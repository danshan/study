package com.shanhh.study.thrift.domain;

import com.shanhh.study.core.verify.Verifier;
import com.shanhh.study.core.verify.VerifierMessages;
import com.shanhh.study.thrift.repository.RepositoryFactory;
import lombok.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author dan.shan
 * @since 2014-10-11 15:03
 */
@Data
public class User {
    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String mobile;
    private Gender gender;
    private Date createTime;
    private Date updateTime;

    public enum Gender {
        UNKNOWN(0),
        MALE(1),
        FEMALE(2);

        @Getter
        private int value;

        Gender(int value) {
            this.value = value;
        }

        public static Gender getByValue(int value) {
            switch (value) {
                case 1: return MALE;
                case 2: return FEMALE;
                default: return UNKNOWN;
            }
        }

    }

    /**
     * save user information
     * @return
     */
    public int create() {
        long start = System.currentTimeMillis();
        checkCreateParams();
        int userId = RepositoryFactory.create(this);
        long end = System.currentTimeMillis();
        LOG.info("user created, userid={}, username={}, use {}ms", userId, username, end - start);
        return userId;
    }

    private static final String ERR_USERNAME_NULL = "username should not be null or empty.";
    private static final String ERR_PASSWORD_NULL = "password should not be null or empty.";

    private void checkCreateParams() {
        new Verifier()
                .isNotEmpty(username, ERR_USERNAME_NULL)
                .isNotEmpty(password, ERR_PASSWORD_NULL)
                .throwIfError(VerifierMessages.MSG_INVALID_INPUTS);
    }

}
