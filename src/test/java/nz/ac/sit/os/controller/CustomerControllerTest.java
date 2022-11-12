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
}