package nz.ac.sit.os.infrastructure.channel.paypal.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import lombok.extern.slf4j.Slf4j;
import nz.ac.sit.os.infrastructure.channel.exception.PaymentChannelException;
import nz.ac.sit.os.infrastructure.channel.exception.PaymentChannelPayloadException;
import nz.ac.sit.os.infrastructure.channel.exception.PaymentChannelTransportException;
import nz.ac.sit.os.infrastructure.channel.paypal.remote.PayPalRemoteAPI;
import nz.ac.sit.os.infrastructure.mybatis.persistence.order.ChannelOrderModel;
import nz.ac.sit.os.application.trade.CallbackPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @program: os
 * @description: Callback from PayPal
 * @author: wangliang
 * @date: 2022-10-26 14:02
 **/
@Slf4j
@Service
public class PayPalCallbackPaymentService implements CallbackPaymentService {
    @Autowired
    private PayPalRemoteAPI payPalRemoteAPI;

    @Value("${paypal.client.id}")
    String clientId;

    @Value("${paypal.client.secret}")
    String clientSecret;

    @Override
    public Optional<ChannelOrderModel> checkoutOrderApprovedCallback(Map<String, String> headers, String requestBody) throws PaymentChannelException {
        final JSONObject callbackData;
        final JSONObject resource;
        final String eventType;
        final String status;
        final String intent;
        final String orderId;

        try {
            callbackData = JSON.parseObject(requestBody);
            Object resourceObj = callbackData.get("resource");
            if (resourceObj == null) {
                throw new PaymentChannelPayloadException("Webhook payload missing 'resource'");
            }

            resource = JSON.parseObject(String.valueOf(callbackData.get("resource")));
            eventType = String.valueOf(callbackData.get("event_type"));
            status = String.valueOf(resource.get("status"));
            intent = String.valueOf(resource.get("intent"));
            orderId = String.valueOf(resource.get("id"));
        }catch (PaymentChannelPayloadException pcpe) {
            throw pcpe;
        }catch (Exception e) {
            throw new PaymentChannelPayloadException("Invalid webhook payload. body=" + requestBody, e);
        }

        if (!("CHECKOUT.ORDER.APPROVED".equals(eventType)
                && "APPROVED".equals(status)
                && "CAPTURE".equals(intent))) {
            log.info("Mismatched webhook event: eventType="+eventType+", " +
                    "intent="+intent+", status="+status );
            return Optional.empty();
        }

        if (orderId == null || orderId.isBlank()) {
            throw new PaymentChannelPayloadException("Webhook payload missing order id. resource="+resource);
        }

        final HttpResponse<Order> res;
        final HttpResponse<Order> captureResp;
        try {
            res = payPalRemoteAPI.getOrder(orderId);
            Order order = res == null ? null : res.result();
            if (order == null) {
                throw new PaymentChannelTransportException("PayPal getOrder returned null result. orderId=" + orderId);
            }

            if (!"APPROVED".equals(order.status())) {
                log.info("PayPal order status is not approved. orderId="+orderId+", status=" + order.status());
                return Optional.empty();
            }

            captureResp = payPalRemoteAPI.captureOrder(orderId, true);
        }catch (PaymentChannelTransportException pce) {
           throw pce;
        }catch (Exception e) {
            throw new PaymentChannelTransportException("PayPal API call failed. orderId="+orderId+", eventType=" + eventType, e);
        }

        // Generate a result object
        ChannelOrderModel channelOrderResult = new ChannelOrderModel();
        String internalPayStatus;

        //0-No pay; 1-Paid; 2-Payment failed
        switch (captureResp.result().status()) {
            case "CREATED":
            case "SAVED":
            case "PAYER_ACTION_REQUIRED":
                internalPayStatus = "0";
                break;
            case "APPROVED":
            case "COMPLETED":
                internalPayStatus = "1";
                break;
            case "VOIDED":
                internalPayStatus = "2";
                break;
            default:
                internalPayStatus = "0";
        }
        channelOrderResult.setPayStatus(internalPayStatus);
        channelOrderResult.setChannelPayOrderNo(captureResp.result().id());

        return Optional.of(channelOrderResult);
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