package com.gms.service.jxc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gms.entity.jxc.Goods;
import com.gms.entity.jxc.ReturnList;
import com.gms.entity.jxc.ReturnListGoods;
import com.gms.dao.repository.GoodsRepository;
import com.gms.dao.repository.GoodsTypeRepository;
import com.gms.dao.repository.ReturnListGoodsRepository;
import com.gms.dao.repository.ReturnListRepository;
import com.gms.service.jxc.ReturnListService;
import com.gms.util.StringUtil;

/**
 * 退货单Service实现类
 * @author jxc_
 *
 */
@Service("returnListService")
@Transactional
public class ReturnListServiceImpl implements ReturnListService{

	@Resource
	private ReturnListRepository returnListRepository;
	
	@Resource
	private ReturnListGoodsRepository returnListGoodsRepository;
	
	@Resource
	private GoodsRepository goodsRepository;
	
	@Resource
	private GoodsTypeRepository goodsTypeRepository;
	
	@Override
	public String getTodayMaxReturnNumber() {
		return returnListRepository.getTodayMaxReturnNumber();
	}

	@Transactional
	public void save(ReturnList returnList, List<ReturnListGoods> returnListGoodsList) {
		// 保存每个退货单商品
		for(ReturnListGoods returnListGoods:returnListGoodsList){
			returnListGoods.setType(goodsTypeRepository.findOne(returnListGoods.getTypeId())); // 设置类别
			returnListGoods.setReturnList(returnList); // 设置退货单
			returnListGoodsRepository.save(returnListGoods);
			// 修改商品库存
			Goods goods=goodsRepository.findOne(returnListGoods.getGoodsId());
			goods.setInventoryQuantity(goods.getInventoryQuantity()-returnListGoods.getNum());
			goods.setState(2);
			goodsRepository.save(goods);
		}
		returnListRepository.save(returnList); // 保存退货单
	}

	@Override
	public ReturnList findById(Integer id) {
		return returnListRepository.findOne(id);
		
	}

	@Override
	public List<ReturnList> list(ReturnList returnList, Direction direction, String... properties) {
		return returnListRepository.findAll(new Specification<ReturnList>(){

			@Override
			public Predicate toPredicate(Root<ReturnList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				if(returnList!=null){
					if(returnList.getSupplier()!=null && returnList.getSupplier().getId()!=null){
						predicate.getExpressions().add(cb.equal(root.get("supplier").get("id"), returnList.getSupplier().getId()));
					}
					if(StringUtil.isNotEmpty(returnList.getReturnNumber())){
						predicate.getExpressions().add(cb.like(root.get("returnNumber"), "%"+returnList.getReturnNumber().trim()+"%"));
					}
					if(returnList.getState()!=null){
						predicate.getExpressions().add(cb.equal(root.get("state"), returnList.getState()));
					}
					if(returnList.getbReturnDate()!=null){
						predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("returnDate"), returnList.getbReturnDate()));
					}
					if(returnList.geteReturnDate()!=null){
						predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("returnDate"), returnList.geteReturnDate()));
					}
					if(returnList.getShopId()!=null && returnList.getShopId().intValue()>0){//默认等于零
						predicate.getExpressions().add(cb.equal(root.get("shopId"), returnList.getShopId()));
					}
				}
				return predicate;
			}
		  },new Sort(direction, properties));
	}

	@Override
	public void delete(Integer id) {
		returnListGoodsRepository.deleteByReturnListId(id);
		returnListRepository.delete(id);
	}

	@Override
	public void update(ReturnList returnList) {
		returnListRepository.save(returnList);
	}

}
