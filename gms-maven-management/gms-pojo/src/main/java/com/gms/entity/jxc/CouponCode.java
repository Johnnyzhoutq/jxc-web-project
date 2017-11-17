package com.gms.entity.jxc;

import javax.persistence.*;
/**
 * @author zhoutianqi
 * @className CouponCode.java
 * @date 2017年11月16日 下午5:24:22
 * @description 优惠券
 */
@Table(name = "t_coupon_code")
public class CouponCode {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 优惠券
     */
    @Column(length=11)
    private Integer couponId;
    /**
     * 优惠券编码
     */
    @Column(length=45)
    private String couponCode;

    /**
     * 用户ID
     */
    @Column(length=11)
    private Integer userId;

    /**
     * 未使用：0  已使用：1
     */
    @Column(length=1)
    private String isUsed;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	/**
     * 获取优惠券编码
     *
     * @return coupon_code - 优惠券编码
     */
    public String getCouponCode() {
        return couponCode;
    }

    /**
     * 设置优惠券编码
     *
     * @param couponCode 优惠券编码
     */
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取未使用：0  已使用：1
     *
     * @return is_used - 未使用：0  已使用：1
     */
    public String getIsUsed() {
        return isUsed;
    }

    /**
     * 设置未使用：0  已使用：1
     *
     * @param isUsed 未使用：0  已使用：1
     */
    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }
}