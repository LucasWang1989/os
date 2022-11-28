package nz.ac.sit.os.domain.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigInteger;

/**
 * @program: os
 * @description: Provider data to front end.
 * @author: wangliang
 * @date: 2022-10-24 14:53
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class ProductModel extends ProductEntity{
    private BigInteger dishNumber;
    private String orderNo;
    private String payStatus;
    private String orderStatus;
    private String cookingStatus;
    private String tableNo;
}