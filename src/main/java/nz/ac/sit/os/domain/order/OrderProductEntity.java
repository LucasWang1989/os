package nz.ac.sit.os.domain.order;

import lombok.Data;
import java.math.BigInteger;

/**
 * @program: os
 * @description: Order product information
 * @author: wangliang
 * @date: 2022-10-24 14:19
 **/
@Data
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
}