package com.gms.service.jxc;

import java.util.List;

import com.gms.entity.jxc.PurchaseListGoods;

/**
 * 进货单商品Service接口
 * @author jxc_
 *
 */
public interface PurchaseListGoodsService {

	/**
	 * 根据进货单id查询所有进货单商品
	 * @param purchaseListId
	 * @return
	 */
	public List<PurchaseListGoods> listByPurchaseListId(Integer purchaseListId);
	
	/**
	 * 根据条件查询进货单商品
	 * @param PurchaseListGoods
	 * @return
	 */
	public List<PurchaseListGoods> list(PurchaseListGoods purchaseListGoods);


}
