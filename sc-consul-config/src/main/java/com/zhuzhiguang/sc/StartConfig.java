package com.zhuzhiguang.sc;

import com.zhuzhiguang.sc.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableConfigurationProperties({MyConfig.class})
public class StartConfig {
    public static void main(String[] args) {
        SpringApplication.run(StartConfig.class);
    }
}
