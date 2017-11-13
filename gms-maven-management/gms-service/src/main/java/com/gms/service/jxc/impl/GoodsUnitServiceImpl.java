package com.gms.service.jxc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gms.entity.jxc.GoodsUnit;
import com.gms.dao.repository.GoodsUnitRepository;
import com.gms.service.jxc.GoodsUnitService;

/**
 * 商品单位Service实现类
 * @author jxc 
 *
 */
@Service("goodsUnitService")
public class GoodsUnitServiceImpl implements GoodsUnitService{

	@Resource
	private GoodsUnitRepository goodsUnitRepository;
	
	@Override
	public List<GoodsUnit> listAll() {
		return goodsUnitRepository.findAll();
	}

	@Override
	public void save(GoodsUnit goodsUnit) {
		goodsUnitRepository.save(goodsUnit);
	}

	@Override
	public void delete(Integer id) {
		goodsUnitRepository.delete(id);
	}

	@Override
	public GoodsUnit findById(Integer id) {
		return goodsUnitRepository.findOne(id);
	}

}
