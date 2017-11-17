package com.gms.entity.jxc;

import java.util.Date;

import javax.persistence.*;
/**
 * @author zhoutianqi
 * @className Shop.java
 * @date 2017年11月16日 下午5:24:22
 * @description 门店
 */
@Entity
@Table(name = "t_shop")
public class Shop {
    /**
     * 门店自增ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 联系电话
     */
    @Column(length=45)
    private String contactNum;

    /**
     * 联系名称
     */
    @Column(length=45)
    private String contactName;

    /**
     * 联系编码
     */
    @Column(length=45)
    private String contactCode;

    /**
     * 业务
     */
    @Column(length=45)
    private String business;
    @Column(length=45)
    private String charter;

    /**
     * 联系方式
     */
    @Column(length=45)
    private String phoneNum;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /**
     * 门店地址
     */
    @Column(length=45)
    private String shopAddress;

    /**
     * 获取门店自增ID
     *
     * @return id - 门店自增ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置门店自增ID
     *
     * @param id 门店自增ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取联系电话
     *
     * @return contact_num - 联系电话
     */
    public String getContactNum() {
        return contactNum;
    }

    /**
     * 设置联系电话
     *
     * @param contactNum 联系电话
     */
    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    /**
     * 获取联系名称
     *
     * @return contact_name - 联系名称
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 设置联系名称
     *
     * @param contactName 联系名称
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * 获取联系编码
     *
     * @return contact_code - 联系编码
     */
    public String getContactCode() {
        return contactCode;
    }

    /**
     * 设置联系编码
     *
     * @param contactCode 联系编码
     */
    public void setContactCode(String contactCode) {
        this.contactCode = contactCode;
    }

    /**
     * 获取业务
     *
     * @return business - 业务
     */
    public String getBusiness() {
        return business;
    }

    /**
     * 设置业务
     *
     * @param business 业务
     */
    public void setBusiness(String business) {
        this.business = business;
    }

    /**
     * @return charter
     */
    public String getCharter() {
        return charter;
    }

    /**
     * @param charter
     */
    public void setCharter(String charter) {
        this.charter = charter;
    }

    /**
     * 获取联系方式
     *
     * @return phone_num - 联系方式
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置联系方式
     *
     * @param phoneNum 联系方式
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取门店地址
     *
     * @return shop_address - 门店地址
     */
    public String getShopAddress() {
        return shopAddress;
    }

    /**
     * 设置门店地址
     *
     * @param shopAddress 门店地址
     */
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
}