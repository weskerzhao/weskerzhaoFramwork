package com.global.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * Created by Guangkuo on 2017/5/17.
 */
public class MD5Util {
    public static String MD5(String str) {
        String newStr = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return newStr;
    }
}
