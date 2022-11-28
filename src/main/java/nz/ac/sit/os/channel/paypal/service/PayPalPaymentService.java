package nz.ac.sit.os.channel.paypal.service;

import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import nz.ac.sit.os.channel.paypal.remote.PayPalRemoteAPI;
import nz.ac.sit.os.common.util.AmountUtil;
import nz.ac.sit.os.common.util.DateUtil;
import nz.ac.sit.os.domain.order.ChannelOrderModel;
import nz.ac.sit.os.service.trade.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: os
 * @description: Service for PayPal
 * @author: wangliang
 * @date: 2022-10-26 14:02
 **/
@Service
public class PayPalPaymentService implements PaymentService {

    @Autowired
    private PayPalRemoteAPI payPalRemoteAPI;

    /**
     * Method to generate sample create order body with <b>CAPTURE</b> intent
     *
     * @return OrderRequest with created order request
     */
    @Override
    public ChannelOrderModel createOrder(ChannelOrderModel channelOrder) {
        // Generate a result object
        ChannelOrderModel channelOrderResult = new ChannelOrderModel();

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("SIT Cafe Inc.")
                .landingPage("BILLING")
                .cancelUrl("https://www.example.com")
                .returnUrl("https://326828j1n4.goho.co/customer/fetch-ordrer-detail?orderNo=" + channelOrder.getPayOrderNo())
                .userAction("CONTINUE")
                .shippingPreference("NO_SHIPPING");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        try {

            PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                    .referenceId(channelOrder.getPayOrderNo())//PUHF
                    .description("Dishes").customId("CUST-Dishes").softDescriptor("")
                    .amountWithBreakdown(new AmountWithBreakdown().currencyCode("NZD").value(AmountUtil.changeF2Y(channelOrder.getPayAmount().toString()))
                                /*.amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("NZD").value("180.00"))
                                .shipping(new Money().currencyCode("NZD").value("20.00"))
                                .handling(new Money().currencyCode("NZD").value("10.00"))
                                .taxTotal(new Money().currencyCode("NZD").value("20.00"))
                                .shippingDiscount(new Money().currencyCode("NZD").value("10.00")))*/)
                /*.items(new ArrayList<Item>() {
                    {
                        add(new Item().name("T-shirt").description("Green XL").sku("sku01")
                                .unitAmount(new Money().currencyCode("NZD").value("90.00"))
                                .tax(new Money().currencyCode("NZD").value("10.00")).quantity("1")
                                .category("PHYSICAL_GOODS"));
                        add(new Item().name("Shoes").description("Running, Size 10.5").sku("sku02")
                                .unitAmount(new Money().currencyCode("NZD").value("45.00"))
                                .tax(new Money().currencyCode("NZD").value("5.00")).quantity("2")
                                .category("PHYSICAL_GOODS"));
                    }
                })*/
                /*.shippingDetail(new ShippingDetail().name(new Name().fullName("John Doe"))
                        .addressPortable(new AddressPortable().addressLine1("123 Townsend St").addressLine2("Floor 6")
                                .adminArea2("San Francisco").adminArea1("CA").postalCode("94107").countryCode("US")))*/;
            purchaseUnitRequests.add(purchaseUnitRequest);
            orderRequest.purchaseUnits(purchaseUnitRequests);

            HttpResponse<Order> response =  payPalRemoteAPI.createOrder(orderRequest, true);
//            HttpResponse<Order> captureRes = payPalRemoteAPI.captureOrder(response.result().id(), true);

            String payStatus = response.result().status();
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
            channelOrderResult.setPayUrl(response.result().links().get(1).href());
            channelOrderResult.setPayTime(DateUtil.getYyyyMMddhhmmss(response.result().createTime()));
            channelOrderResult.setChannelPayOrderNo(response.result().id());
        }catch (Exception e) {
            System.out.println("Generate purchaseUnitRequest error.");
        }

        return channelOrderResult;
    }
}