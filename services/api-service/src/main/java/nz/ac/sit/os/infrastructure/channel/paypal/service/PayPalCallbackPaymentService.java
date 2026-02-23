package nz.ac.sit.os.infrastructure.channel.paypal.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import nz.ac.sit.os.infrastructure.channel.paypal.remote.PayPalRemoteAPI;
import nz.ac.sit.os.persistence.order.ChannelOrderModel;
import nz.ac.sit.os.application.trade.CallbackPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;


/**
 * @program: os
 * @description: Callback from PayPal
 * @author: wangliang
 * @date: 2022-10-26 14:02
 **/
@Service
public class PayPalCallbackPaymentService implements CallbackPaymentService {

    @Autowired
    private PayPalRemoteAPI payPalRemoteAPI;

    @Value("${paypal.client.id}")
    String clientId;

    @Value("${paypal.client.secret}")
    String clientSecret;

    @Override
    public ChannelOrderModel checkoutOrderApprovedCallback(Map<String, String> headers, String requestBody) throws Exception {
        // Generate a result object
        ChannelOrderModel channelOrderResult = new ChannelOrderModel();

        //            JSONObject callbackData = JSON.parseObject(JSON.parseObject(requestBody).get("resource").toString());
        JSONObject callbackData = JSON.parseObject(requestBody);
        JSONObject resource = JSON.parseObject(callbackData.get("resource").toString());
        String eventType= callbackData.get("event_type").toString();

        if (!("CHECKOUT.ORDER.APPROVED".equals(eventType)
                && "APPROVED".equals(resource.get("status").toString())
                && "CAPTURE".equals(resource.get("intent").toString()))) {
            System.out.println("Unsubscribed event type:" + eventType);
            return null;
        }

        String orderId = resource.get("id").toString();
        HttpResponse<Order> res = payPalRemoteAPI.getOrder(orderId);

        if("APPROVED".equals(res.result().status())) {
            HttpResponse<Order> captureResp = payPalRemoteAPI.captureOrder(orderId, true);

            String payStatus = captureResp.result().status();
            //0-No pay; 1-Paid; 2-Payment failed
            switch (payStatus) {
                case "CREATED":
                case "SAVED":
                case "PAYER_ACTION_REQUIRED":
                    channelOrderResult.setPayStatus("0");
                    break;
                case "APPROVED":
                    channelOrderResult.setPayStatus("1");
                    break;
                case "VOIDED":
                    channelOrderResult.setPayStatus("2");
                    break;
                case "COMPLETED":
                    channelOrderResult.setPayStatus("1");
                    break;
                default:
                    channelOrderResult.setPayStatus("0");
            }
//                    channelOrderResult.setPayTime(DateUtil.getYyyyMMddhhmmss(captureResp.result().createTime()));
            channelOrderResult.setChannelPayOrderNo(captureResp.result().id());
        }


        return channelOrderResult;
    }

    public static void main(String[] args) {
        String str = "{\n" +
                "    \"id\":\"WH-COC11055RA711503B-4YM959094A144403T\",\n" +
                "    \"create_time\":\"2018-04-16T21:21:49.000Z\",\n" +
                "    \"event_type\":\"CHECKOUT.ORDER.APPROVED\",\n" +
                "    \"resource_type\":\"checkout-order\",\n" +
                "    \"resource_version\":\"2.0\",\n" +
                "    \"summary\":\"An order has been approved by buyer\",\n" +
                "    \"resource\":{\n" +
                "        \"id\":\"5O190127TN364715T\",\n" +
                "        \"status\":\"APPROVED\",\n" +
                "        \"intent\":\"CAPTURE\",\n" +
                "        \"payer\":{\n" +
                "            \"name\":{\n" +
                "                \"given_name\":\"John\",\n" +
                "                \"surname\":\"Doe\"\n" +
                "            },\n" +
                "            \"email_address\":\"customer@example.com\",\n" +
                "            \"payer_id\":\"QYR5Z8XDVJNXQ\"\n" +
                "        },\n" +
                "        \"purchase_units\":[\n" +
                "            {\n" +
                "                \"reference_id\":\"d9f80740-38f0-11e8-b467-0ed5f89f718b\",\n" +
                "                \"amount\":{\n" +
                "                    \"currency_code\":\"USD\",\n" +
                "                    \"value\":\"100.00\"\n" +
                "                },\n" +
                "                \"payee\":{\n" +
                "                    \"email_address\":\"merchant@example.com\"\n" +
                "                },\n" +
                "                \"shipping\":{\n" +
                "                    \"method\":\"United States Postal Service\",\n" +
                "                    \"address\":{\n" +
                "                        \"address_line_1\":\"2211 N First Street\",\n" +
                "                        \"address_line_2\":\"Building 17\",\n" +
                "                        \"admin_area_2\":\"San Jose\",\n" +
                "                        \"admin_area_1\":\"CA\",\n" +
                "                        \"postal_code\":\"95131\",\n" +
                "                        \"country_code\":\"US\"\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        ],\n" +
                "        \"create_time\":\"2018-04-01T21:18:49Z\",\n" +
                "        \"update_time\":\"2018-04-01T21:20:49Z\",\n" +
                "        \"links\":[\n" +
                "            {\n" +
                "                \"href\":\"https://api.paypal.com/v2/checkout/orders/5O190127TN364715T\",\n" +
                "                \"rel\":\"self\",\n" +
                "                \"method\":\"GET\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"href\":\"https://api.paypal.com/v2/checkout/orders/5O190127TN364715T/capture\",\n" +
                "                \"method\":\"POST\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"links\":[\n" +
                "        {\n" +
                "            \"href\":\"https://api.paypal.com/v1/notifications/webhooks-events/WH-COC11055RA711503B-4YM959094A144403T\",\n" +
                "            \"rel\":\"self\",\n" +
                "            \"method\":\"GET\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"href\":\"https://api.paypal.com/v1/notifications/webhooks-events/WH-COC11055RA711503B-4YM959094A144403T/resend\",\n" +
                "            \"rel\":\"resend\",\n" +
                "            \"method\":\"POST\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"event_version\":\"1.0\"\n" +
                "}";
        JSONObject jo = JSON.parseObject(str);
        JSONObject jo2 = JSON.parseObject(jo.get("resource").toString());
        System.out.println(jo2.get("status"));

    }
}