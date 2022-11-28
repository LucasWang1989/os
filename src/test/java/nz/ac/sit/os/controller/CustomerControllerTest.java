package nz.ac.sit.os.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: os
 * @description:
 * @author: wangliang
 * @date: 2022-10-24 15:04
 **/
@SpringBootTest
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @Test
    void fetchProduct() {
        customerController.fetchProduct(1);
    }

    @Test
    void checkout() {
        String data = "{\"id\":\"20\",\"amount\":2,\"price\":\"18\",\"imagePaht\":\"http://localhost:8080/customer/img/tof/b3858ccf-949a-4f33-9bb2-1e66fce643c2.jpg\",\"name\":\"Noodles\"},{\"id\":\"3\",\"amount\":2,\"price\":\"15\",\"imagePaht\":\"http://localhost:8080/customer/img/tof/up3.jpg\",\"name\":\"Coffee\"}";
        String tableNo = "1";
        customerController.checkOut(data, tableNo);
    }
}