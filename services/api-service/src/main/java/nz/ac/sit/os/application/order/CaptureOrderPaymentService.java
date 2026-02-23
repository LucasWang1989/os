package nz.ac.sit.os.application.order;

import nz.ac.sit.os.infrastructure.channel.paypal.service.PayPalCallbackPaymentService;
import nz.ac.sit.os.mapper.ChannelPayOrderMapper;
import nz.ac.sit.os.persistence.order.ChannelOrderModel;
import nz.ac.sit.os.persistence.order.MercOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CaptureOrderPaymentService {
    @Autowired
    private PayPalCallbackPaymentService payPalCallbackPaymentService;
    @Autowired
    private ChannelPayOrderMapper channelPayOrderMapper;
    @Autowired
    private OrderService orderService;

    public void handle(Map<String, String> headers, String requestBody) {

        try {
            ChannelOrderModel channelOrderResult = payPalCallbackPaymentService.checkoutOrderApprovedCallback(headers, requestBody);

            if(channelOrderResult != null) {
                channelPayOrderMapper.updateChannelOrderByChannelOrderNo(channelOrderResult);
                ChannelOrderModel channelOrder = channelPayOrderMapper.acquireChannelOrderByChannelOrderNo(channelOrderResult);

                MercOrderModel mercOrder = new MercOrderModel();
                mercOrder.setOrderNo(channelOrder.getPayOrderNo());
                mercOrder.setPayStatus(channelOrderResult.getPayStatus());
                orderService.updateMercOrder(mercOrder);
            }
        } catch (Exception e) {
            System.out.println("Capture Exception:" + e);
        }
    }
}
