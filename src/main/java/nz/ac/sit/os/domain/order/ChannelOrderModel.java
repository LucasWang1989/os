package nz.ac.sit.os.domain.order;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: os
 * @description: Provider data to front end.
 * @author: wangliang
 * @date: 2022-10-24 14:53
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class ChannelOrderModel extends ChannelOrderEntity {
    private String payUrl;
}