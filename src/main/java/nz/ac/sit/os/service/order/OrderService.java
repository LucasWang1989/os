package nz.ac.sit.os.service.order;

import nz.ac.sit.os.common.util.DateUtil;
import nz.ac.sit.os.domain.order.MercOrderModel;
import nz.ac.sit.os.domain.product.ProductModel;
import nz.ac.sit.os.mapper.MercOrderMapper;
import nz.ac.sit.os.mapper.ProductDefMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @program: os
 * @description: Operation about order
 * @author: wangliang (Lucas Wang)
 * @email: lucas.wang.1024@gmail.com
 * @date: 2022-10-27 13:30
 **/
@Service
public class OrderService {

    @Autowired
    private MercOrderMapper mercOrderMapper;
    @Autowired
    private ProductDefMapper productDefMapper;


    public List<MercOrderModel> fetchOrder() {

        return mercOrderMapper.fetchOrder();
    }

    public List<ProductModel> fetchOrderProduct(String orderNo) {
        return productDefMapper.fetchOrderProduct(orderNo);
    }

    public MercOrderModel fetchLatestWait2CookOrder () {
        return mercOrderMapper.fetchLatestWait2CookOrder();
    }

    public void updateMercOrder (MercOrderModel mercOrder) {
        String payStatus = mercOrder.getPayStatus();

        if(StringUtils.isNotBlank(payStatus)
                || "1".equals(payStatus)
                || "2".equals(payStatus)) {
            mercOrder.setOrderStatus("2");
        }
        mercOrder.setUpdateDate(DateUtil.getDate());
        mercOrder.setUpdateTime(DateUtil.getTime());
        mercOrderMapper.updateMercOrder(mercOrder);
    }
}