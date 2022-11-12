package nz.ac.sit.os.channel.paypal.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Event;
import com.paypal.base.Constants;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import nz.ac.sit.os.channel.paypal.remote.PayPalRemoteAPI;
import nz.ac.sit.os.common.util.AmountUtil;
import nz.ac.sit.os.common.util.DateUtil;
import nz.ac.sit.os.domain.order.ChannelOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;


/**
 * @program: os
 * @description: Callback from PayPal
 * @author: wangliang
 * @date: 2022-10-26 14:02
 **/
@Service
public class PayPalCallbackPayService {

    @Autowired
    private PayPalRemoteAPI payPalRemoteAPI;

    public ChannelOrderModel checkoutOrderApprovedCallback(Map<String, String> headers, String requestBody) {
        // Generate a result object
        ChannelOrderModel channelOrderResult = new ChannelOrderModel();

        try {
            // ### Api Context
            APIContext apiContext = new APIContext("AdTn2zuHD-OtbdQR1zlP0j1wetpySRAeZRAQMDSG7QB0J3uc3nk769_YychiLAKjxQwjbUmrPBI_f2S_",
                    "EBt5qlxE2gZ__wUhpwvM1pOpYi3qdI9OAE6fxHNGGrJzVRHL6ocjkqeP7u9WqYnE_MW_YSPpsfD6X9xu", "sandbox");
            // Set the webhookId that you received when you created this webhook.
            apiContext.addConfiguration(Constants.PAYPAL_WEBHOOK_ID, "6FJ32264S1231642V");
            Boolean result = Event.validateReceivedEvent(apiContext, headers, requestBody);
            System.out.println("Result is " + result);
//            LOGGER.info("Webhook Validated:  " + result);

            if(result) {
                JSONObject callbackData = JSON.parseObject(JSON.parseObject(requestBody).get("resource").toString());
                if ("APPROVED".equals(callbackData.get("status").toString())
                        && "CAPTURE".equals(callbackData.get("intent").toString())) {
                    HttpResponse<Order> captureResp = payPalRemoteAPI.captureOrder(callbackData.get("id").toString(), true);

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
            }

//            ResultPrinter.addResult(req, resp, "Webhook Validated:  ", CreditCard.getLastRequest(),
//                    CreditCard.getLastResponse(), null);
        } catch (Exception e) {
//            ResultPrinter.addResult(req, resp, "Webhook Validated:  ", CreditCard.getLastRequest(),
//                    null, e.getMessage());
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