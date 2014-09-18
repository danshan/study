package com.shanhh.study.hessian.demo.spring.impl;

import com.shanhh.study.hessian.demo.spring.AddService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author dan.shan
 * @since 2014-09-18 14:51
 */
@Service
public class AddServiceImpl implements AddService {

    private static final Logger logger = LoggerFactory.getLogger(AddService.class);

    @Override
    public int add(int a, int b) {
        logger.info("add(int a, int b) is invoked");
        return a + b;
    }

}
