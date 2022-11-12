package nz.ac.sit.os.channel.paypal.remote;

import java.io.IOException;
import com.paypal.orders.*;
import nz.ac.sit.os.channel.paypal.util.PayPalClient;
import org.json.JSONObject;
import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: os
 * @description: Communicate with Paypal
 * @author: wangliang
 * @date: 2022-10-22 22:07
 **/
@Service
public class PayPalRemoteAPI {

    @Autowired
    private PayPalClient payPalClient;

    /**
     * Method to create order
     *
     * @param debug true = print response data
     * @return HttpResponse<Order> response received from API
     * @throws IOException Exceptions from API if any
     */
    public HttpResponse<Order> createOrder(OrderRequest remoteRequest, boolean debug) throws IOException {
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.header("prefer","return=representation");
        request.requestBody(remoteRequest);
        HttpResponse<Order> response = payPalClient.client().execute(request);

        if (debug) {
            if (response.statusCode() == 201) {
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Status: " + response.result().status());
                System.out.println("Order ID: " + response.result().id());
                System.out.println("Intent: " + response.result().checkoutPaymentIntent());
                System.out.println("Links: ");
                for (LinkDescription link : response.result().links()) {
                    System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
                }
                System.out.println("Total Amount: " + response.result().purchaseUnits().get(0).amountWithBreakdown().currencyCode()
                        + " " + response.result().purchaseUnits().get(0).amountWithBreakdown().value());
                System.out.println("Full response body:");
                System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
            }
        }
        return response;
    }

    /**
     * Creating empty body for capture request. We can set the payment source if
     * required.
     *
     * @return OrderRequest request with empty body
     */
    public OrderRequest buildRequestBody4Capture() {
        return new OrderRequest();
    }

    /**
     * Method to capture order after creation. Valid approved order Id should be
     * passed an argument to this method.
     *
     * @param orderId Order ID from createOrder response
     * @param debug   true = print response data
     * @return HttpResponse<Order> response received from API
     * @throws IOException Exceptions from API if any
     */
    public HttpResponse<Order> captureOrder(String orderId, boolean debug) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        request.requestBody(buildRequestBody4Capture());
        HttpResponse<Order> response = payPalClient.client().execute(request);
        if (debug) {
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Status: " + response.result().status());
            System.out.println("Order ID: " + response.result().id());
            System.out.println("Links: ");
            for (LinkDescription link : response.result().links()) {
                System.out.println("\t" + link.rel() + ": " + link.href());
            }
            System.out.println("Capture ids:");
            for (PurchaseUnit purchaseUnit : response.result().purchaseUnits()) {
                for (Capture capture : purchaseUnit.payments().captures()) {
                    System.out.println("\t" + capture.id());
                }
            }
            System.out.println("Buyer: ");
            Payer buyer = response.result().payer();
            System.out.println("\tEmail Address: " + buyer.email());
            System.out.println("\tName: " + buyer.name().givenName() + " " + buyer.name().surname());
            System.out.println("Full response body:");
            System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
        }
        return response;
    }

    /**
     * Method to perform sample GET on an order
     *
     * @throws IOException Exceptions from API if any
     */
    public void getOrder(String orderId) throws IOException {
        OrdersGetRequest request = new OrdersGetRequest(orderId);
        HttpResponse<Order> response = payPalClient.client().execute(request);
        System.out.println("Full response body:");
        System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
    }


    /**
     * This is the driver function which invokes the createOrder function to create
     * an sample order.
     */
    public static void main(String args[]) {
//        try {
//            new PayPalRemoteAPI().createOrder(true);
//        } catch (com.paypal.http.exceptions.HttpException e) {
//            System.out.println(e.getLocalizedMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}