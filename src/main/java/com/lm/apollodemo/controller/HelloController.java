package com.lm.apollodemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 测试
 * @Author: louiema1n
 * @Time: 2020/5/6 14:19
 */
@RestController
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    String port;

    @GetMapping("hi")
    public String hi(String name) {
        logger.debug( "debug log..." );
        logger.info( "info log..." );
        logger.warn( "warn log..." );
        return "hi " + name + " ,i am from port:" + port;
    }
}
