package nz.ac.sit.os.api;

import nz.ac.sit.os.application.order.CaptureOrderPaymentService;
import nz.ac.sit.os.common.error.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: os
 * @description: Management of products
 * @author: wangliang
 * @date: 2022-10-24 14:09
 **/
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private CaptureOrderPaymentService captureOrderPaymentService;

    @RequestMapping("/checkout-order-approved")
    public ResponseEntity checkoutOrderApproved(HttpServletRequest request, HttpServletResponse resp) throws BizException {

        // Simple helper method to help you extract the headers from HttpServletRequest object.
        Map< String, String > map = new HashMap< String, String >();
        @SuppressWarnings("rawtypes")
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        // Simple helper method to fetch request data as a string from HttpServletRequest object.
        String body;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            System.out.print("Convert request error");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    System.out.print("Convert request error");
                }
            }
        }

        body = stringBuilder.toString();
        if (body == null || body.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        captureOrderPaymentService.handle(map, body);

        return ResponseEntity.ok().build();
    }
}