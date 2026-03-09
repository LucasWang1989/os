package nz.ac.sit.os.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nz.ac.sit.os.mapper.MercOrderMapper;
import nz.ac.sit.os.persistence.order.MercOrderModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @program: os
 * @description: Integration test for order-related operations
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 08/03/2026 22:14
 **/

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CustomerApiIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MercOrderMapper mercOrderMapper;

    @Test
    void should_create_order_and_persist() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/customer/checkout/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "tableNo": 11,
                                   "items": [
                                     {
                                       "id": "1",
                                       "amount": 2,
                                       "name": "Pizza",
                                       "price": 1600,
                                       "imagePath": "img/tof/up1.jpg"
                                     },
                                     {
                                       "id": "2",
                                       "amount": 2,
                                       "name": "Hamburger",
                                       "price": 1200,
                                       "imagePath": "img/tof/up2.jpg"
                                     },
                                     {
                                       "id": "3",
                                       "amount": 2,
                                       "name": "Coffee",
                                       "price": 1500,
                                       "imagePath": "img/tof/up3.jpg"
                                     },
                                     {
                                       "id": "4",
                                       "amount": 1,
                                       "name": "Bread",
                                       "price": 2000,
                                       "imagePath": "img/tof/up4.jpg"
                                     }
                                   ]
                                 }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderNo").exists())
                .andExpect(jsonPath("$.payUrl").exists())
                .andReturn();


        /**
         * Check data from database
         */
        String response = result.getResponse().getContentAsString();
        JsonNode json = new ObjectMapper().readTree(response);
        String orderNo = json.get("orderNo").asText();

        assertEquals("1", mercOrderMapper.fetchOrderByOrderNo(orderNo)
                .map(MercOrderModel::getOrderStatus)
                .orElse("-1"));
    }

    @Test
    void should_fetch_all_products() throws Exception {
        mockMvc.perform(get("/api/customer/menus?tableNo=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tableNo").value("1"))
                .andExpect(jsonPath("$.products").isNotEmpty());
    }
}