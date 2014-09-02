package com.shanhh.study.unittest.utils;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * @author dan.shan
 * @since 2014-04-27 下午5:00
 */
public class RegexUtilsTest {

    @Test
    public void testIsMobile_Null() {
        assertFalse(RegexUtils.isMobile(null));
    }

    @Test
    public void testIsMobile_Success() {
        assertTrue(RegexUtils.isMobile("13400000000"));
        assertTrue(RegexUtils.isMobile("13500000000"));
        assertTrue(RegexUtils.isMobile("13600000000"));
        assertTrue(RegexUtils.isMobile("13700000000"));
        assertTrue(RegexUtils.isMobile("13800000000"));
        assertTrue(RegexUtils.isMobile("13900000000"));
        assertTrue(RegexUtils.isMobile("18600000000"));
        assertTrue(RegexUtils.isMobile("18500000000"));
        assertTrue(RegexUtils.isMobile("15600000000"));

        assertFalse(RegexUtils.isMobile(""));
        assertFalse(RegexUtils.isMobile("1560000000"));
        assertFalse(RegexUtils.isMobile("156000000000"));
        assertFalse(RegexUtils.isMobile("fuck"));
        assertFalse(RegexUtils.isMobile("手机"));
        assertFalse(RegexUtils.isMobile("01560000000"));

    }

    @Test
    public void testIsEmail_Null() {
        assertFalse(RegexUtils.isEmail(null));
    }

    @Test
    public void testIsEmail_Success() {
        assertFalse(RegexUtils.isEmail(""));
        assertFalse(RegexUtils.isEmail("邮件"));
        assertFalse(RegexUtils.isEmail("fuck@vipshop"));
        assertFalse(RegexUtils.isEmail("fuck@gfw@vipshop.com"));

        assertTrue(RegexUtils.isEmail("fuck@vipshop.com"));
        assertTrue(RegexUtils.isEmail("dan.shan@vipshop.com"));
        assertTrue(RegexUtils.isEmail("dan-shan@vipshop.com"));
        assertTrue(RegexUtils.isEmail("dan_shan@vipshop.com"));
        assertTrue(RegexUtils.isEmail("dan.shan@vipshop.com.cn"));
    }

}
