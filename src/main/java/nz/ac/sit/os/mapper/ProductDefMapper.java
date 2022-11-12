package nz.ac.sit.os.mapper;

import nz.ac.sit.os.domain.order.OrderProductModel;
import nz.ac.sit.os.domain.product.ProductModel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @program: os
 * @description: Mapper for product definition table.
 * @author: wangliang
 * @date: 2022-10-24 14:30
 **/
@Mapper
public interface ProductDefMapper {

    void addProduct(ProductModel product);

    List<ProductModel> fetchProduct();

    List<ProductModel> fetchOrderProduct(String orderNo);
}
