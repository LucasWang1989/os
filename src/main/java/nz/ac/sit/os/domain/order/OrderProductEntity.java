package nz.ac.sit.os.domain.order;


import java.math.BigInteger;

/**
 * @program: os
 * @description: Order product information
 * @author: wangliang
 * @date: 2022-10-24 14:19
 **/
public class OrderProductEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private String orderNo;
    private String productId;
    private BigInteger amount;
    private String updateDate;
    private String updateTime;
    private String createdDate;
    private String createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        return "OrderProductEntity{" +
                "id='" + id + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", productId='" + productId + '\'' +
                ", amount='" + amount + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}