package nz.ac.sit.os.controller;

import nz.ac.sit.os.common.util.AmountUtil;
import nz.ac.sit.os.domain.order.MercOrderModel;
import nz.ac.sit.os.domain.product.ProductModel;
import nz.ac.sit.os.mapper.ProductDefMapper;
import nz.ac.sit.os.service.order.OrderService;
import nz.ac.sit.os.service.product.ProductDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: os
 * @description: Operation for admin
 * @author: wangliang (Lucas Wang)
 * @email: lucas.wang.1024@gmail.com
 * @date: 2022-10-27 13:25
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductDefMapper productDefMapper;
    @Autowired
    private ProductDefService productDefService;

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView();

        if(!username.isEmpty()
                && !password.isEmpty()
                && "admin".equals(username)
                && "123456".equals(password)) {
            session.setAttribute("user",username+password);
            return this.fetchOrder();
        }else {
            mav.setViewName("/admin/login.jsp");
            mav.addObject("errorMessage", "Incorrect user name or password, please try again.");
        }
        return mav;
    }

    @RequestMapping("/add-product")
    public ModelAndView addProduct(@RequestParam String name,
                                   @RequestParam String price,
                                   @RequestParam String imagePath,
                                   @RequestParam String desc) {

        ProductModel product = new ProductModel();
        product.setName(name);
        product.setPrice(new BigInteger(AmountUtil.changeY2F(price)));
        product.setImagePath(imagePath);
        product.setDesc(desc);
        productDefService.addProduct(product);

        return this.fetchProduct();
    }
    @RequestMapping("/fetch-product")
    public ModelAndView fetchProduct() {
        List<ProductModel> products = productDefMapper.fetchProduct();

        ModelAndView mav = new ModelAndView("/admin/product-list.jsp");
        mav.addObject("products", products);
        return mav;
    }

    @RequestMapping("/fetch-order")
    public ModelAndView fetchOrder() {
        List<MercOrderModel> orders = orderService.fetchOrder();

        ModelAndView mav = new ModelAndView("/admin/orders-list.jsp");
        mav.addObject("orders", orders);
        return mav;
    }

    @RequestMapping("/fetch-order-product")
    public ModelAndView fetchOrderProduct(@RequestParam String orderNo) {
        List<ProductModel> orderProducts = orderService.fetchOrderProduct(orderNo);

        ModelAndView mav = new ModelAndView("/admin/orders-detail.jsp");
        mav.addObject("orderProducts", orderProducts);
        return mav;
    }

    @RequestMapping("/fetch-latest-waited-order")
    public ModelAndView fetchLatestWaitedOrder() {
        ModelAndView wait2CookProductMAV = new ModelAndView();

        MercOrderModel wait2CookOrder = orderService.fetchLatestWait2CookOrder();
        if(wait2CookOrder != null) {
            wait2CookProductMAV = this.fetchOrderProduct(wait2CookOrder.getOrderNo());
        }else {
            wait2CookProductMAV.addObject("orderProducts", new ArrayList<ProductModel>());
        }
        wait2CookProductMAV.setViewName("/admin/orders-detail-chef.jsp");
        return wait2CookProductMAV;
    }

    @RequestMapping("/update-order-cooking-status")
    public ModelAndView updateOrderCookingStatus(String orderNo, String mode) {

        MercOrderModel mercOrder = new MercOrderModel();
        mercOrder.setOrderNo(orderNo);
        mercOrder.setCookingStatus("1");
        orderService.updateMercOrder(mercOrder);

        // 0-Screen display 1-Admin
        if("0".equals(mode)) {
            return fetchLatestWaitedOrder();
        }else {
            return fetchOrderProduct(orderNo);
        }
    }

    @PostMapping("/upload-dish-photo")
    public String uploadDishPhoto(HttpServletRequest request, @RequestParam("avatar") MultipartFile fileUpload){
        String fileName = fileUpload.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID()+suffixName;
        try {
            String path = request.getSession().getServletContext().getRealPath("customer/img/tof/");
            fileUpload.transferTo(new File(path + fileName));
            return "img/tof/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}