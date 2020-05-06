package com.lm.apollodemo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.sun.applet2.preloader.event.ConfigEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.Set;

/**
 * @Description: 日志配置
 * @Author: louiema1n
 * @Time: 2020/5/6 15:08
 */
@Configuration
public class LoggerConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggerConfig.class);
    public static final String LOGGER_LEVEL = "logging.level.";

    @Autowired
    private LoggingSystem loggingSystem;

    @ApolloConfig
    private Config apolloConfig;

    @ApolloConfigChangeListener
    private void configChangeListener(ConfigChangeEvent configEvent) {
        refreshLoggingLevel();
    }

    @PostConstruct
    private void refreshLoggingLevel() {
        Set<String> propertyNames = apolloConfig.getPropertyNames();
        for (String propertyName : propertyNames) {
            if (StringUtils.containsIgnoreCase(propertyName, LOGGER_LEVEL)) {
                // 默认值为info
                String strLevel = apolloConfig.getProperty(propertyName, "info");
                LogLevel logLevel = LogLevel.valueOf(strLevel.toUpperCase());
                loggingSystem.setLogLevel(propertyName.replace(LOGGER_LEVEL, ""), logLevel);
                logger.info("{}:{}", propertyName, strLevel);
            }
        }
    }
}
