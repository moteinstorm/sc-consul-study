package com.zhuzhiguang.sc.controller;

import com.netflix.loadbalancer.ILoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TestProvider {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;





    /**
     * 获取服务列表
     * @return
     */
    @RequestMapping("services")
    public List<String> getServices(){

        List<String> services = discoveryClient.getServices();
        return  services;

    }

    /**
     * 获取所有的服务列表
     * @return
     */
    @RequestMapping("getservice")
    public  List<ServiceInstance>  getservice(){

        List<ServiceInstance> provider = discoveryClient.getInstances("providerName");
        return provider;
    }


    @RequestMapping("bcall")
    public String balanceCall(){

        // 随机获取一个服务
        ServiceInstance instance = loadBalancerClient.choose("providerName");
        // 获取服务的资源地址
        String url = instance.getUri().toString() + "/test";

        RestTemplate template = new RestTemplate();
        // 发起调用
        String s = template.getForObject(url, String.class);
        System.out.println("return s is " + s);
        return s;
    }


    /**
     * 调用某一个服务
     * @return
     */
    @RequestMapping("call")
    public String  call(){
        // 获取实例ID 为 provider 的服务
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");

        if(provider!=null && provider.size()>0){
            //
            RestTemplate template = new RestTemplate();
            ServiceInstance instance = provider.get(0);
            // 利用rest 调用
            String forObject = template.getForObject(instance.getUri().toString() + "/test", String.class);
            System.out.println("return is " + forObject);
            return forObject;
        }
        return "no service";

    }

}
