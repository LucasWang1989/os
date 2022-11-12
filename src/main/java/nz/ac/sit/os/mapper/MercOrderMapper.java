package nz.ac.sit.os.mapper;

import nz.ac.sit.os.domain.order.MercOrderModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

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

    void updateMercOrder(MercOrderModel mercOrderModel);


    MercOrderModel fetchLatestWait2CookOrder();
}
