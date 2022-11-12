package nz.ac.sit.os.mapper;

import nz.ac.sit.os.domain.order.ChannelOrderModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: os
 * @description: Mapper for channel order table.
 * @author: wangliang
 * @date: 2022-10-24 14:30
 **/
@Mapper
public interface ChannelPayOrderMapper {

    void storeChannelOrder(ChannelOrderModel channelOrder);

    void updateChannelOrder(ChannelOrderModel channelOrder);

    void updateChannelOrderByChannelOrderNo(ChannelOrderModel channelOrder);

    ChannelOrderModel acquireChannelOrderByChannelOrderNo(ChannelOrderModel channelOrder);

}
