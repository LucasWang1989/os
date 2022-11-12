package nz.ac.sit.os.mapper;

import nz.ac.sit.os.domain.order.OrderProductModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @program: os
 * @description: Mapper for order product table.
 * @author: wangliang
 * @date: 2022-10-24 14:30
 **/
@Mapper
public interface OrderProductMapper {
    void storeOrderProduct(List<OrderProductModel> orderProducts);
}
