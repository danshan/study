package com.shanhh.testcase.demo.utils;

import java.util.regex.Pattern;

/**
 * tools for checking format
 * @author dan.shan
 * @since 2014-04-27 下午4:55
 */
public class RegexUtils {

    /**
     * 手机正则表达式
     */
    private static final Pattern MOBILE_PTN = Pattern.compile("^1[3458]\\d{9}$");
    /**
     * email 正则表达式
     */
    private static final Pattern EMAIL_PTN = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");

    public static boolean isMobile(String str) {
        return str != null && MOBILE_PTN.matcher(str).find();
    }

    public static boolean isEmail(String str) {
        return str != null && EMAIL_PTN.matcher(str).find();
    }
}
