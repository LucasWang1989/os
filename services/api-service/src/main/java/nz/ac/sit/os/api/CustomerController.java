package nz.ac.sit.os.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import nz.ac.sit.os.persistence.product.ProductModel;
import nz.ac.sit.os.mapper.ProductDefMapper;
import nz.ac.sit.os.application.order.OrderService;
import nz.ac.sit.os.application.trade.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
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

    @RequestMapping("/checkout")
    public ModelAndView checkOut(String data, String tableNo) {
        try {
            String decodeData = URLDecoder.decode(data,"UTF-8");
            decodeData = "["+decodeData+"]";

            List<ProductModel> dishes = new ArrayList<>();
            JSONArray jsa = JSON.parseArray(decodeData);
            for (int i = 0; i < jsa.size(); i++) {
                JSONObject jo = jsa.getJSONObject(i);
                if(jo.isEmpty()) continue;

                ProductModel productModel = new ProductModel();
                productModel.setId((String)jo.get("id"));
                productModel.setPrice(new BigInteger((String)jo.get("price"))
                        .multiply(new BigInteger("100")));
                productModel.setDishNumber(new BigInteger(jo.get("amount").toString()));
                dishes.add(productModel);
            }

            String payUrl = tradeService.createOrder(tableNo, dishes);
            ModelAndView mav = new ModelAndView(new RedirectView(payUrl));
            return mav;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/fetch-ordrer-detail")
    public ModelAndView fetchOrderDetail(@RequestParam String orderNo) {

        List<ProductModel> orderProducts = orderService.fetchOrderProduct(orderNo);

        ModelAndView mav = new ModelAndView("/customer/orders-detail.jsp");
        mav.addObject("orderProducts", orderProducts);
        return mav;
    }
}