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
}