package nz.ac.sit.os.domain.order;

import java.math.BigInteger;

/**
 * @program: os
 * @description: Product information
 * @author: wangliang
 * @date: 2022-10-24 14:19
 **/
public class ChannelOrderEntity {
    private static final long serialVersionUID = 1L;

    private String id;
    private String payOrderNo;
    private String payType;
    private String channelNo;
    private String channelPayOrderNo;
    private String payStatus; //0-No pay; 1-Paid; 2-Payment failed
    private BigInteger payAmount;
    private String payTime;
    private String remark;
    private String channelMercId;
    private String updateTime;
    private String createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelPayOrderNo() {
        return channelPayOrderNo;
    }

    public void setChannelPayOrderNo(String channelPayOrderNo) {
        this.channelPayOrderNo = channelPayOrderNo;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public BigInteger getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigInteger payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChannelMercId() {
        return channelMercId;
    }

    public void setChannelMercId(String channelMercId) {
        this.channelMercId = channelMercId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "ChannelOrderEntity{" +
                "id='" + id + '\'' +
                ", payOrderNo='" + payOrderNo + '\'' +
                ", payType='" + payType + '\'' +
                ", channelNo='" + channelNo + '\'' +
                ", channelPayOrderNo='" + channelPayOrderNo + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", payAmount=" + payAmount +
                ", payTime='" + payTime + '\'' +
                ", remark='" + remark + '\'' +
                ", channelMercId='" + channelMercId + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}