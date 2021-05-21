package org.yangxin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author
 * 消费者
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApp
{
    public static void main( String[] args )
    {

        SpringApplication.run(ConsumerApp.class,args);
    }

    @RestController
    public class NacosController{


        @Autowired
        private LoadBalancerClient loadBalancerClient;
        @Autowired
        private RestTemplate restTemplate;

        private final String  appName = "consumer";

        @GetMapping("/echo/app-name")
        public String echoAppName(){
            ServiceInstance serviceInstance = loadBalancerClient.choose("yinhai-provider");
            String path = String.format("http://%s:%s/echo/%s",serviceInstance.getHost(),serviceInstance.getPort(),appName);
            System.out.println("request path:" +path);
            return restTemplate.getForObject(path,String.class);

        }

    }
    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }
}
