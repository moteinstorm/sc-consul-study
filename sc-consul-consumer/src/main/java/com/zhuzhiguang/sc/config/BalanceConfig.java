package com.zhuzhiguang.sc.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BalanceConfig {

    @Bean
    public IRule myRule(){
        // 指定使用随机的负载均衡的算法
        return  new RandomRule();
       // return  new RoundRobinRule();
    }
}
