package nz.ac.sit.os.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class OrderGenerators {
    private static String dateFormat = "yyyyMMddhh24mmsssss";

    public static String nextOrderNo() {
        return DateFormatUtils.format(new Date(), dateFormat);
    }


    public static void main(String[] args) {
        System.out.print(nextOrderNo());
    }
}
