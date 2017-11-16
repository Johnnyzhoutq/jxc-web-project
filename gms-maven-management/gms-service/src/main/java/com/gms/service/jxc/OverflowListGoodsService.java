package com.gms.service.jxc;

import java.util.List;

import com.gms.entity.jxc.OverflowListGoods;

/**
 * 报溢单商品Service接口
 * @author jxc_
 *
 */
public interface OverflowListGoodsService {

	/**
	 * 根据报溢单id查询所有报溢单商品
	 * @param overflowListId
	 * @return
	 */
	public List<OverflowListGoods> listByOverflowListId(Integer overflowListId);


}
