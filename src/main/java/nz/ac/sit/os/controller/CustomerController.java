package nz.ac.sit.os.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import nz.ac.sit.os.domain.product.ProductModel;
import nz.ac.sit.os.mapper.ProductDefMapper;
import nz.ac.sit.os.service.order.OrderService;
import nz.ac.sit.os.service.trade.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @program: os
 * @description: Management of products
 * @author: wangliang
 * @date: 2022-10-24 14:09
 **/
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ProductDefMapper productDefMapper;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/fetch-menu")
    public ModelAndView fetchProduct(@RequestParam Integer tableNo,
                                     HttpServletRequest req,
                                     HttpServletResponse res
                                     ) {

        String URI = req.getRequestURI();
        StringBuffer URL = req.getRequestURL();
        String contextPath = req.getContextPath();

        ServletContext cont = req.getServletContext();
        int minor = cont.getMinorVersion();
        int major = cont.getMajorVersion();
        String info = cont.getServerInfo();

        String appNm = cont.getServletContextName();
        String cntPath = cont.getContextPath();
        Enumeration<String> pns =  cont.getInitParameterNames();

        String realPath = cont.getRealPath("admin/images/like.png");
        try {
            URL resr = cont.getResource("admin/orders-detail-chef.jsp");
            InputStream resrIn = cont.getResourceAsStream("admin/orders-detail-chef.jsp");
            String mime = cont.getMimeType("webapp/admin/images/like.png");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        List<ProductModel> products = productDefMapper.fetchProduct();

        ModelAndView mav = new ModelAndView("/customer/menu.jsp");
        mav.addObject("products", products);
        mav.addObject("tableNo", tableNo);

        return mav;
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