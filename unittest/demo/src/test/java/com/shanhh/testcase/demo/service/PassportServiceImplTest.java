package com.shanhh.testcase.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.AssertJUnit.assertTrue;

/**
 * base class for test service framework
 * @author dan.shan
 * @since 2014-04-27 下午5:24
 */
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class PassportServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PassportService passportService;

    @Test(description = "register a passport, but username is null",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "^username cannot be null\\.")
    public void testRegister_UsenrameNull() {
        passportService.register(null, "password");
    }

    @Test(description = "register a passport, but password is null",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "^username cannot be null\\.")
    public void testRegister_PasswordNull() {
        passportService.register(null, "password");
    }

    @Test(description = "register a passport, but username already exists",
            expectedExceptions = IllegalStateException.class,
            expectedExceptionsMessageRegExp = "^username already exists,.*")
    public void testRegister_UsernameExist() {
        passportService.register("exist@vipshop.com", "password");
    }

    @Test(description = "register a passport, but username format incorrect",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "^username format incorrect.*")
    public void testRegister_UsernameFormatIncorrect() {
        passportService.register("incorrect_username", "password");
    }

    @Test(description = "register a passport")
    public void testRegister_Success() {
        int passportId = passportService.register(UUID.randomUUID().toString() + "@vipshop.com", "password");
        assertTrue(passportId > 0);
    }

}
