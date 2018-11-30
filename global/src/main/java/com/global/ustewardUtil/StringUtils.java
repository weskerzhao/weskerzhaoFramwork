package com.global.ustewardUtil;

/**
 * 字符串处理工具类
 * <p>
 * Created by Guangkuo on 2018/2/6.
 */
public class StringUtils {

    /**
     * 使用正则表达去掉小数点后的无效位
     *
     * @param number 需要处理的float值
     * @return 去掉无效位后的字符串
     */
    public static String hideInvalidBit(double number) {
        return hideInvalidBit(String.valueOf(number));
    }

    /**
     * 使用正则表达去掉小数点后的无效位
     *
     * @param number 需要处理的float值
     * @return 去掉无效位后的字符串
     */
    public static String hideInvalidBit(float number) {
        return hideInvalidBit(String.valueOf(number));
    }

    /**
     * 使用正则表达去掉小数点后的无效位
     *
     * @param number 需要处理的字符串
     * @return 去掉无效位后的字符串
     */
    public static String hideInvalidBit(String number) {
        if (number.indexOf(".") > 0) {
            number = number.replaceAll("0+?$", "");//去掉后面无用的零
            number = number.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return number;
    }

    public static String FloathideInvalidBit(float number) {
        String num = String.valueOf(number);
        if (num.indexOf(".") > 0) {
            num = num.replaceAll("0+?$", "");//去掉后面无用的零
            num = num.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return num;
    }
}
