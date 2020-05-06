package com.lm.apollodemo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApolloDemoApplicationTests {

    @Test
    void contextLoads() {
        Config appConfig = ConfigService.getAppConfig();
        String someKey = "timeout";
        String someDefaultValue = "null";
        String val = appConfig.getProperty(someKey, someDefaultValue);
        System.out.println(val);
    }

}
