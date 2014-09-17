package com.shanhh.study.mybatis.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dan.shan
 * @since 2014-09-17 14:31
 */
@Data
@NoArgsConstructor
public class Shop {

    private int shopId;
    private String shopName;
    private String city;

}
