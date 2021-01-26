package com.lx.attendance.model.vo;

import java.math.BigDecimal;

/**
 * @author: dantong.xu
 * @date: 2018/10/23
 * @describe: 订单实体类
 */
public class OrderVO {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 订单状态
     */
    private int status;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 下单时间
     */
    private String orderTime;
    /**
     * 下单用户id
     */
    private Long orderUserId;
    /**
     * 完成时间
     */
    private String completeTime;
    /**
     * 商品id
     */
    private Integer commodityId;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品类型
     */
    private Integer type;
    /**
     *商品类型名称
     */
    private String typeName;
    /**
     * 商品图片
     */
    private String commodityImg;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     *用户名
     */
    private String username;
    /**
     *用户地址
     */
    private String address;
    /**
     *用户联系方式
     */
    private String tel;
    /**
     * 订单状态名
     */
    private String statusName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Long getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Long orderUserId) {
        this.orderUserId = orderUserId;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCommodityImg() {
        return commodityImg;
    }

    public void setCommodityImg(String commodityImg) {
        this.commodityImg = commodityImg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
