package com.shanhh.study.mybatis.dao.po;

import com.shanhh.study.mybatis.domain.Shop;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author dan.shan
 * @since 2014-09-17 14:33
 */
@Data
@NoArgsConstructor
public class ShopPO {

    private int shopId;
    private String shopName;
    private String city;

    public ShopPO(Shop domain) {
        this();
        if (domain == null) {
            return;
        }

        BeanUtils.copyProperties(domain, this);
    }

    public Shop toDomain() {
        Shop domain = new Shop();
        BeanUtils.copyProperties(this, domain);
        return domain;
    }
}
