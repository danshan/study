package com.shanhh.testcase.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * @author dan.shan
 * @since 2014-04-27 下午7:47
 */
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class PassportRepositoryImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PassportRepository passportRepository;

    @Test
    public void testCheckUsernameExist_Exist() throws Exception {
        assertTrue(passportRepository.checkUsernameExist("exist@vipshop.com"));
    }

    @Test
    public void testCheckUsernameExist_NotExist() throws Exception {
        assertFalse(passportRepository.checkUsernameExist("not_exist@vipshop.com"));
    }

    @Test
    public void testSavePassport() throws Exception {
        int passportId = passportRepository.savePassport(UUID.randomUUID().toString() + "@vipshop.com", "password");
        assertTrue(passportId > 0);
    }

}
