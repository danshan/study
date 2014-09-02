package com.shanhh.testcase.demo.service;

import com.shanhh.testcase.demo.repository.PassportRepository;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * base class for test service framework
 * @author dan.shan
 * @since 2014-04-27 下午5:24
 */
public class PassportServiceMockTest {

    private PassportService passportService = new PassportServiceImpl();
    private PassportRepository passportRepository;

    private Field field;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        passportRepository = mock(PassportRepository.class);

        field = PassportServiceImpl.class.getDeclaredField("passportRepository");
        field.setAccessible(true);
        field.set(passportService, passportRepository);
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        reset(passportRepository);
    }

    @Test(description = "register a passport, but username already exists",
            expectedExceptions = IllegalStateException.class,
            expectedExceptionsMessageRegExp = "^username already exists,.*")
    public void testRegister_UsernameExist() {
        String username = UUID.randomUUID().toString() + "@vipshop.com";

        when(passportRepository.checkUsernameExist(username)).thenReturn(true);
        passportService.register(username, "password");
    }

    @Test(description = "register a passport")
    public void testRegister_Success() {
        String username = UUID.randomUUID().toString() + "@vipshop.com";
        String password = "password";
        int expectedPassportId = 100;

        when(passportRepository.checkUsernameExist(username)).thenReturn(false);
        when(passportRepository.savePassport(username, password)).thenReturn(expectedPassportId);

        int passportId = passportService.register(username, password);
        assertEquals(expectedPassportId, passportId);
    }


    @Test(description = "register a passport, but throw an sqlexception",
            expectedExceptions = SQLException.class)
    public void testRegister_Failed() {
        String username = UUID.randomUUID().toString() + "@vipshop.com";
        String password = "password";

        when(passportRepository.checkUsernameExist(username)).thenThrow(SQLException.class);

        passportService.register(username, password);
        verify(passportRepository.checkUsernameExist(username));
        verify(passportRepository.savePassport(username, password), never());
    }

}
