package nz.ac.sit.os.domain.product;

import java.math.BigInteger;

/**
 * @program: os
 * @description: Provider data to front end.
 * @author: wangliang
 * @date: 2022-10-24 14:53
 **/
public class ProductModel extends ProductEntity{
    private BigInteger dishNumber;
    private String orderNo;
    private String payStatus;
    private String orderStatus;
    private String cookingStatus;
    private String tableNo;

    public BigInteger getDishNumber() {
        return dishNumber;
    }

    public void setDishNumber(BigInteger dishNumber) {
        this.dishNumber = dishNumber;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCookingStatus() {
        return cookingStatus;
    }

    public void setCookingStatus(String cookingStatus) {
        this.cookingStatus = cookingStatus;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "dishNumber=" + dishNumber +
                ", orderNo='" + orderNo + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", cookingStatus='" + cookingStatus + '\'' +
                ", tableNo='" + tableNo + '\'' +
                '}';
    }
}