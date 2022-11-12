package nz.ac.sit.os.service.product;

import nz.ac.sit.os.common.util.DateUtil;
import nz.ac.sit.os.domain.product.ProductModel;
import nz.ac.sit.os.mapper.ProductDefMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: os
 * @description: Management of product infomation
 * @author: wangliang (Lucas Wang)
 * @email: lucas.wang.1024@gmail.com
 * @date: 2022-10-31 20:05
 **/
@Service
public class ProductDefService {
    @Autowired
    private ProductDefMapper productDefMapper;

    public void addProduct(ProductModel product) {
        product.setCreatedDate(DateUtil.getDate());
        product.setCreatedTime(DateUtil.getTime());
        product.setUpdateDate(DateUtil.getDate());
        product.setUpdateTime(DateUtil.getTime());
        productDefMapper.addProduct(product);
    }
}