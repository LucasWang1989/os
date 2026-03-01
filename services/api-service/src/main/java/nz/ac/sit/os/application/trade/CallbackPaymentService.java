package nz.ac.sit.os.application.trade;

import nz.ac.sit.os.infrastructure.channel.exception.PaymentChannelException;
import nz.ac.sit.os.persistence.order.ChannelOrderModel;
import java.util.Map;
import java.util.Optional;

/**
 * @program: os
 * @description: Process of webhook from channels
 * @author: wangliang (Lucas Wang)
 * @email: lucas.wang.1024@gmail.com
 * @date: 2022-11-28 15:52
 **/
public interface CallbackPaymentService {

    Optional<ChannelOrderModel> checkoutOrderApprovedCallback(Map<String, String> headers, String requestBody) throws PaymentChannelException;

}
