
package com.waterbase.http.common;

/**
 * 服务器返回码规则,用来处理不同状况 例如：token过期。。。。。。
 * 作者：Laughing on 2018/5/27 14:21
 * 邮箱：719240226@qq.com
 */
public class ErrorCode {
    public static final int TOKEN_NOT_EXIST = 1000;//token不存在
    public static final int TOKEN_INVALID = 1001;//token无效

    public static final int TOKEN_PAST = 2;//token过期
    public static final int RE_LOGIN = 4020;//用户密码在别的设备上登录

}
