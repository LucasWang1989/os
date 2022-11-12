package nz.ac.sit.os.domain.order;

/**
 * @program: os
 * @description: Provider data to front end.
 * @author: wangliang
 * @date: 2022-10-24 14:53
 **/
public class ChannelOrderModel extends ChannelOrderEntity {
    private String payUrl;

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    @Override
    public String toString() {
        return "ChannelOrderModel{" +
                "payUrl='" + payUrl + '\'' +
                '}';
    }
}