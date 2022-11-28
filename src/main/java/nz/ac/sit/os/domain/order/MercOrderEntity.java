package nz.ac.sit.os.domain.order;

import lombok.Data;
import java.math.BigInteger;

/**
 * @program: os
 * @description: Product information
 * @author: wangliang
 * @date: 2022-10-24 14:19
 **/
@Data
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
}