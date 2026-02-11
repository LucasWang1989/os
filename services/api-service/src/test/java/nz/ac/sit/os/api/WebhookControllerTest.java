package nz.ac.sit.os.api;

import nz.ac.sit.os.infrastructure.channel.paypal.service.PayPalCallbackPaymentService;
import nz.ac.sit.os.common.util.DateUtil;
import nz.ac.sit.os.persistence.order.ChannelOrderModel;
import nz.ac.sit.os.persistence.order.MercOrderModel;
import nz.ac.sit.os.mapper.ChannelPayOrderMapper;
import nz.ac.sit.os.mapper.MercOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @program: os
 * @description:
 * @author: wangliang (Lucas Wang)
 * @email: lucas.wang.1024@gmail.com
 * @date: 2022-10-27 22:15
 **/
@SpringBootTest
class WebhookControllerTest {
    @Autowired
    private WebhookController webhookController;
//    @Autowired
//    private PayPalCallbackPaymentService payPalCallbackPaymentService;
    @Autowired
    private ChannelPayOrderMapper channelPayOrderMapper;
    @Autowired
    private MercOrderMapper mercOrderMapper;

    @Test
    void test() throws Exception {
        MockMvc mm = MockMvcBuilders.standaloneSetup(new WebhookController()).build();
        mm.perform(MockMvcRequestBuilders.get("/webhook/checkout-order-approved")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ChannelOrderModel channelOrderResult = new ChannelOrderModel();
        channelOrderResult.setChannelPayOrderNo("3WJ87457CP519803A");
        channelOrderResult.setPayStatus("1");
        channelPayOrderMapper.updateChannelOrderByChannelOrderNo(channelOrderResult);
        ChannelOrderModel channelOrder = channelPayOrderMapper.acquireChannelOrderByChannelOrderNo(channelOrderResult);

        MercOrderModel mercOrder = new MercOrderModel();
        mercOrder.setOrderNo(channelOrder.getPayOrderNo());
        mercOrder.setPayStatus("1");
        mercOrder.setOrderStatus("2");
        mercOrder.setUpdateDate(DateUtil.getDate());
        mercOrder.setUpdateTime(DateUtil.getTime());
        mercOrderMapper.updateMercOrder(mercOrder);
    }
}