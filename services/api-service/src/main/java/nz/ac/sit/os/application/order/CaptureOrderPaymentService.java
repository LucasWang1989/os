package nz.ac.sit.os.application.order;

import nz.ac.sit.os.common.error.BizException;
import nz.ac.sit.os.common.error.code.CommonErrorCodes;
import nz.ac.sit.os.common.error.code.PaymentErrorCodes;
import nz.ac.sit.os.infrastructure.channel.exception.PaymentChannelPayloadException;
import nz.ac.sit.os.infrastructure.channel.exception.PaymentChannelTransportException;
import nz.ac.sit.os.infrastructure.channel.paypal.service.PayPalCallbackPaymentService;
import nz.ac.sit.os.infrastructure.mybatis.mapper.ChannelPayOrderMapper;
import nz.ac.sit.os.infrastructure.mybatis.persistence.order.ChannelOrderModel;
import nz.ac.sit.os.infrastructure.mybatis.persistence.order.MercOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class CaptureOrderPaymentService {
    @Autowired
    private PayPalCallbackPaymentService payPalCallbackPaymentService;
    @Autowired
    private ChannelPayOrderMapper channelPayOrderMapper;
    @Autowired
    private QueryOrderService queryOrderService;

    public void handle(Map<String, String> headers, String requestBody) throws BizException {

        try {
            Optional<ChannelOrderModel> channelOrderOpt = payPalCallbackPaymentService.checkoutOrderApprovedCallback(headers, requestBody);

            channelOrderOpt.ifPresent(channelOrderResult -> {
                channelPayOrderMapper.updateChannelOrderByChannelOrderNo(channelOrderResult);
                ChannelOrderModel channelOrder = channelPayOrderMapper.acquireChannelOrderByChannelOrderNo(channelOrderResult);

                MercOrderModel mercOrder = new MercOrderModel();
                mercOrder.setOrderNo(channelOrder.getPayOrderNo());
                mercOrder.setPayStatus(channelOrderResult.getPayStatus());
                queryOrderService.updateMercOrder(mercOrder);
            });

        } catch (PaymentChannelPayloadException e) {
            throw new BizException(PaymentErrorCodes.PAYLOAD_INVALID, e.getMessage(), e);
        } catch (PaymentChannelTransportException e) {
            throw new BizException(PaymentErrorCodes.PAYMENT_PROVIDER_UNAVAILABLE, e.getMessage(), e);
        } catch (Exception e) {
            throw new BizException(CommonErrorCodes.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
