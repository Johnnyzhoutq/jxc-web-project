package com.gms.entity.jxc;

import javax.persistence.*;
/**
 * @author zhoutianqi
 * @className Coupon.java
 * @date 2017年11月16日 下午5:24:22
 * @description 优惠券
 */
@Entity
@Table(name = "t_coupon")
public class Coupon {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
	@JoinColumn(name="shopId")
	private Shop shop; // 商户

    /**
     * 优惠券批次号
     */
    @Column(length=11)
    private Integer batchNum;

    /**
     * 是否打开
     */
    @Column(length=1)
    private String isOpen;

    /**
     * 领取次数（每人限领）
     */
    @Column(length=45)
    private String couponCount;

    /**
     * 有效开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private String expiryDateStart;

    /**
     * 有效结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private String expiryDateStop;

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

    public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	/**
     * 获取优惠券批次号
     *
     * @return batch_num - 优惠券批次号
     */
    public Integer getBatchNum() {
        return batchNum;
    }

    /**
     * 设置优惠券批次号
     *
     * @param batchNum 优惠券批次号
     */
    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
    }

    /**
     * 获取是否打开
     *
     * @return is_open - 是否打开
     */
    public String getIsOpen() {
        return isOpen;
    }

    /**
     * 设置是否打开
     *
     * @param isOpen 是否打开
     */
    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * 获取领取次数（每人限领）
     *
     * @return coupon_count - 领取次数（每人限领）
     */
    public String getCouponCount() {
        return couponCount;
    }

    /**
     * 设置领取次数（每人限领）
     *
     * @param couponCount 领取次数（每人限领）
     */
    public void setCouponCount(String couponCount) {
        this.couponCount = couponCount;
    }

    /**
     * 获取有效开始时间
     *
     * @return expiry_date_start - 有效开始时间
     */
    public String getExpiryDateStart() {
        return expiryDateStart;
    }

    /**
     * 设置有效开始时间
     *
     * @param expiryDateStart 有效开始时间
     */
    public void setExpiryDateStart(String expiryDateStart) {
        this.expiryDateStart = expiryDateStart;
    }

    /**
     * 获取有效结束时间
     *
     * @return expiry_date_stop - 有效结束时间
     */
    public String getExpiryDateStop() {
        return expiryDateStop;
    }

    /**
     * 设置有效结束时间
     *
     * @param expiryDateStop 有效结束时间
     */
    public void setExpiryDateStop(String expiryDateStop) {
        this.expiryDateStop = expiryDateStop;
    }
}