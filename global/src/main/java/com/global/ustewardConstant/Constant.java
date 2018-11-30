package com.global.ustewardConstant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 项目中的一些常量
 * <p>
 * Created by Guangkuo on 2018/2/5.
 */
public final class Constant {
    /**
     * 项目中的日期格式
     */
    public static final DateFormat APP_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    /**
     * 项目中的时间格式
     */
    public static final DateFormat APP_TIME_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
    /**
     * 订单状态--未使用
     */
    public static final String ORDER_STATE_NO_USE = "NO_USE";
    /**
     * 订单状态--使用中
     */
    public static final String ORDER_STATE_IN_USE = "IN_USE";
    /**
     * 订单状态--已完成
     * 完成状态包含以下子状态（未支付、已支付、已退款）
     * ORDER_STATE_NON_PAYMENT / ORDER_STATE_ACCOUNT_PAID / ORDER_STATE_REFUNDED
     */
    public static final String ORDER_STATE_COMPLETED = "COMPLETED";
    /**
     * 订单状态--已取消
     */
    public static final String ORDER_STATE_CANCELED = "CANCELED";

    /**
     * 订单支付状态--未支付
     */
    public static final String ORDER_STATE_NON_PAYMENT = "NON_PAYMENT";
    /**
     * 订单支付状态--已支付
     */
    public static final String ORDER_STATE_ACCOUNT_PAID = "ACCOUNT_PAID";
    /**
     * 订单支付状态--已退款
     */
    public static final String ORDER_STATE_REFUNDED = "REFUNDED";
    /**
     * 用户权限--白名单
     */
    public static final String USER_AUTHORITY_WHITE_LIST = "1";
    /**
     * 用户权限--现金缴费
     */
    public static final String USER_AUTHORITY_CASH_PAY = "2";
    /**
     * 用户权限--预约车位
     */
    public static final String USER_AUTHORITY_PARKING_SPACE_ORDER = "3";
    /**
     * 用户权限--悠停卡
     */
    public static final String USER_AUTHORITY_CARD = "4";
    /**
     * 搜索列表中 item 的类型--白名单
     */
    public static final String SEARCH_SUPER_WHITE_LIST = "SUPER_WHITE_LIST";
    /**
     * 搜索列表中 item 的类型--悠停卡
     */
    public static final String SEARCH_PARKING_CARD_LIST = "PARKING_CARD_LIST";

    /**
     * 预约停车
     */
    public static final String ORDER_PARKING_CAR = "PARKING_RESERVATION";
    /**
     * 临时停车
     */
    public static final String TEMPORARY_PARKING_CAR = "PARKING_TEMPORARY";
}
