package nz.ac.sit.os.api;

import nz.ac.sit.os.api.dto.CheckoutRequest;
import nz.ac.sit.os.persistence.product.ProductModel;
import nz.ac.sit.os.mapper.ProductDefMapper;
import nz.ac.sit.os.application.order.OrderService;
import nz.ac.sit.os.application.trade.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @program: os
 * @description: Management of products
 * @author: wangliang
 * @date: 2022-10-24 14:09
 **/
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private ProductDefMapper productDefMapper;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/menus")
    public Map<String, Object> fetchProduct(@RequestParam Integer tableNo) {

        List<ProductModel> products = productDefMapper.fetchProduct();

        Map<String, Object> res = new HashMap<>();
        res.put("tableNo", tableNo);
        res.put("products", products);

        return res;
    }

    @PostMapping(value = "/checkout/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> checkOut(@RequestBody CheckoutRequest req) {

        String tableNo = req.getTableNo();
        List<CheckoutRequest.CheckoutItem> items = req.getItems();

        if (tableNo == null || tableNo.isBlank() || items == null || items.isEmpty()) {
            return Map.of("errorMsg", "Table numbers or dish items are empty. Please try again.");
        }

        List<ProductModel> dishes = new ArrayList<>();
        for (CheckoutRequest.CheckoutItem it : items) {
            if (it == null || it.getId() == null || it.getAmount() == null) continue;

            ProductModel productModel = new ProductModel();
            productModel.setId(it.getId());

            if (it.getPrice() != null) {
                productModel.setPrice(BigInteger.valueOf(it.getPrice()));
            }

            productModel.setDishNumber(BigInteger.valueOf(it.getAmount()));
            dishes.add(productModel);
        }

        String payUrl = tradeService.createOrder(tableNo, dishes);
        return Map.of("payUrl", payUrl);
    }

    @GetMapping("/checkout/orders/{orderNo}")
    public Map<String, Object> fetchOrderDetail(@PathVariable String orderNo) {

        List<ProductModel> orderProducts = orderService.fetchOrderProduct(orderNo);

        Map<String, Object> res = new HashMap<>();
        res.put("orderProducts", orderProducts);
        return res;
    }

}