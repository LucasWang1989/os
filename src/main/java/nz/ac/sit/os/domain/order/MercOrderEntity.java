package nz.ac.sit.os.domain.order;

import java.math.BigInteger;

/**
 * @program: os
 * @description: Product information
 * @author: wangliang
 * @date: 2022-10-24 14:19
 **/
public class MercOrderEntity {
    private static final long serialVersionUID = 1L;


    private Integer id;
    private String orderNo;
    private String tableNo;
    private String mercId;
    private String shopId;
    private String payType;
    private String orderStatus;
    private String refundStatus;
    private String payStatus;
    private BigInteger orderAmount;
    private BigInteger residueRefundAmount;
    private BigInteger alreadyRefundAmount;
    private String productDesc;
    private String orderType;
    private String extPara;
    private String updateDate;
    private String updateTime;
    private String createdDate;
    private String createdTime;
    private String reserved;
    private String reserved1;
    private String reserved2;
    private String cookingStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getMercId() {
        return mercId;
    }

    public void setMercId(String mercId) {
        this.mercId = mercId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public BigInteger getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigInteger orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigInteger getResidueRefundAmount() {
        return residueRefundAmount;
    }

    public void setResidueRefundAmount(BigInteger residueRefundAmount) {
        this.residueRefundAmount = residueRefundAmount;
    }

    public BigInteger getAlreadyRefundAmount() {
        return alreadyRefundAmount;
    }

    public void setAlreadyRefundAmount(BigInteger alreadyRefundAmount) {
        this.alreadyRefundAmount = alreadyRefundAmount;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getExtPara() {
        return extPara;
    }

    public void setExtPara(String extPara) {
        this.extPara = extPara;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getCookingStatus() {
        return cookingStatus;
    }

    public void setCookingStatus(String cookingStatus) {
        this.cookingStatus = cookingStatus;
    }

    @Override
    public String toString() {
        return "MercOrderEntity{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", tableNo='" + tableNo + '\'' +
                ", mercId='" + mercId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", payType='" + payType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", refundStatus='" + refundStatus + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", orderAmount=" + orderAmount +
                ", residueRefundAmount=" + residueRefundAmount +
                ", alreadyRefundAmount=" + alreadyRefundAmount +
                ", productDesc='" + productDesc + '\'' +
                ", orderType='" + orderType + '\'' +
                ", extPara='" + extPara + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", reserved='" + reserved + '\'' +
                ", reserved1='" + reserved1 + '\'' +
                ", reserved2='" + reserved2 + '\'' +
                ", cookingStatus='" + cookingStatus + '\'' +
                '}';
    }
}