package nz.ac.sit.os.service.trade;

import nz.ac.sit.os.common.util.DateUtil;
import nz.ac.sit.os.common.util.OrderGenerators;
import nz.ac.sit.os.domain.order.ChannelOrderModel;
import nz.ac.sit.os.domain.order.MercOrderModel;
import nz.ac.sit.os.domain.order.OrderProductModel;
import nz.ac.sit.os.domain.product.ProductModel;
import nz.ac.sit.os.mapper.ChannelPayOrderMapper;
import nz.ac.sit.os.mapper.MercOrderMapper;
import nz.ac.sit.os.mapper.OrderProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: os
 * @description: Operation about order
 * @author: wangliang
 * @date: 2022-10-25 21:53
 **/
@Service
public class TradeService {

    @Autowired
    private MercOrderMapper mercOrderMapper;
    @Autowired
    private ChannelPayOrderMapper channelPayOrderMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private ApplicationContext appContext;


    @Transactional
    public String createOrder(String tableNo, List<ProductModel> dishes) {
        BigInteger totalAmount = new BigInteger("0");
        String orderNo = OrderGenerators.nextOrderNo();
        List<OrderProductModel> orderProducts = new ArrayList<>();

        for(ProductModel dish : dishes) {
            totalAmount = totalAmount.add(dish.getPrice().multiply(dish.getDishNumber()));

            OrderProductModel orderProduct = new OrderProductModel();
            orderProduct.setOrderNo(orderNo);
            orderProduct.setProductId(dish.getId());
            orderProduct.setAmount(dish.getDishNumber());
            orderProduct.setUpdateDate(DateUtil.getDate());
            orderProduct.setUpdateTime(DateUtil.getTime());
            orderProduct.setCreatedDate(DateUtil.getDate());
            orderProduct.setCreatedTime(DateUtil.getTime());
            orderProducts.add(orderProduct);
        }

        // Create an inner order
        MercOrderModel mercOrder = new MercOrderModel();
        mercOrder.setOrderNo(orderNo);
        mercOrder.setTableNo(tableNo);
        mercOrder.setMercId("8362303340873518");
        mercOrder.setShopId("01");
        mercOrder.setPayType("10");
        mercOrder.setOrderStatus("1");
        mercOrder.setRefundStatus("0");
        mercOrder.setPayStatus("0");
        mercOrder.setOrderAmount(totalAmount);
        mercOrder.setResidueRefundAmount(totalAmount);
        mercOrder.setAlreadyRefundAmount(new BigInteger("0"));
        mercOrder.setProductDesc("");
        mercOrder.setOrderType("1");
        mercOrder.setExtPara("");
        mercOrder.setUpdateDate(DateUtil.getDate());
        mercOrder.setUpdateTime(DateUtil.getTime());
        mercOrder.setCreatedDate(DateUtil.getDate());
        mercOrder.setCreatedTime(DateUtil.getTime());
        mercOrder.setReserved("");
        mercOrder.setReserved1("");
        mercOrder.setReserved2("");
        mercOrder.setCookingStatus("0");
        mercOrderMapper.storeMercOrder(mercOrder);

        //Create a channel order
        ChannelOrderModel channelOrder = new ChannelOrderModel();
        channelOrder.setPayOrderNo(orderNo);
        channelOrder.setPayType("10");
        channelOrder.setChannelNo("payPal000010");
        channelOrder.setChannelPayOrderNo("");
        channelOrder.setPayStatus("0");
        channelOrder.setPayAmount(totalAmount);
        channelOrder.setPayTime("");
        channelOrder.setRemark("Order System from SIT");
        channelOrder.setChannelMercId("AdTn2zuHD-OtbdQR1zlP0j1wetpySRAeZRAQMDSG7QB0J3uc3nk769_YychiLAKjxQwjbUmrPBI_f2S_");
        channelOrder.setUpdateTime(DateUtil.getDate() + DateUtil.getTime());
        channelOrder.setCreatedTime(DateUtil.getDate() + DateUtil.getTime());
        channelPayOrderMapper.storeChannelOrder(channelOrder);

        // Add relationship between orders and order products
        orderProductMapper.storeOrderProduct(orderProducts);

        // Choose a channel from routing service
        String channelName = "payPal"; //payment routing service will be implemented soon.

        // Communicate with a payment channel to create an order
        PaymentService paymentService = (PaymentService)appContext.getBean(channelName + "PaymentService");
        ChannelOrderModel channelOrderResult = paymentService.createOrder(channelOrder);

        // Update channel order information from payment companies or banks
        channelOrderResult.setPayOrderNo(orderNo);
        channelPayOrderMapper.updateChannelOrder(channelOrderResult);

        return channelOrderResult.getPayUrl();
    }

}