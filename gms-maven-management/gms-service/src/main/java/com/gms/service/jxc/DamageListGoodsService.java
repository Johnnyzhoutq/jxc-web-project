package com.gms.service.jxc;

import java.util.List;

import com.gms.entity.jxc.DamageListGoods;

/**
 * 报损单商品Service接口
 * @author jxc_
 *
 */
public interface DamageListGoodsService {

	/**
	 * 根据报损单id查询所有报损单商品
	 * @param damageListId
	 * @return
	 */
	public List<DamageListGoods> listByDamageListId(Integer damageListId);


}
