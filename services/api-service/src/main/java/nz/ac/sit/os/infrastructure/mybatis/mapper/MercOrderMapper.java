package nz.ac.sit.os.infrastructure.mybatis.mapper;

import nz.ac.sit.os.infrastructure.mybatis.persistence.order.MercOrderModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

/**
 * @program: os
 * @description: Mapper for channel order table.
 * @author: wangliang
 * @date: 2022-10-24 14:30
 **/
@Mapper
public interface MercOrderMapper {

    void storeMercOrder(MercOrderModel mercOrder);

    List<MercOrderModel> fetchOrder();

    Optional<MercOrderModel> fetchOrderByOrderNo(String orderNo);

    void updateMercOrder(MercOrderModel mercOrderModel);

    MercOrderModel fetchLatestWait2CookOrder();
}
