package nz.ac.sit.os.application.trade;

import nz.ac.sit.os.infrastructure.mybatis.persistence.order.ChannelOrderModel;

/**
 * @program: os
 * @description: Interface for payment features
 * @author: wangliang (Lucas Wang)
 * @email: lucas.wang.1024@gmail.com
 * @date: 2022-11-27 16:33
 **/
public interface PaymentService {

    ChannelOrderModel createOrder(ChannelOrderModel channelOrder);
}
