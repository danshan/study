package com.shanhh.study.thrift.po;

import com.shanhh.study.thrift.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author dan.shan
 * @since 2014-10-11 14:59
 */
@Data
@NoArgsConstructor
public class UserPO {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String mobile;
    private Integer gender;
    private Date createTime;
    private Date updateTime;

    public UserPO(User user) {

        if (user == null) {
            return;
        }

        BeanUtils.copyProperties(user, this, new String[]{"gender"});
        gender = user.getGender() == null ? null : user.getGender().getValue();
    }

    public User toDomain() {
        User domain = new User();
        BeanUtils.copyProperties(this, domain, new String[]{"gender"});
        domain.setGender(this.gender == null ? null : User.Gender.getByValue(this.gender));
        return domain;
    }
}
