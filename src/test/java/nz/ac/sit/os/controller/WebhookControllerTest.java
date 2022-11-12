package nz.ac.sit.os.controller;

import nz.ac.sit.os.channel.paypal.service.PayPalCallbackPayService;
import nz.ac.sit.os.common.util.DateUtil;
import nz.ac.sit.os.domain.order.ChannelOrderModel;
import nz.ac.sit.os.domain.order.MercOrderModel;
import nz.ac.sit.os.mapper.ChannelPayOrderMapper;
import nz.ac.sit.os.mapper.MercOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    @Autowired
    private PayPalCallbackPayService payPalCallbackPayService;
    @Autowired
    private ChannelPayOrderMapper channelPayOrderMapper;
    @Autowired
    private MercOrderMapper mercOrderMapper;

    @Test
    void test() {

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