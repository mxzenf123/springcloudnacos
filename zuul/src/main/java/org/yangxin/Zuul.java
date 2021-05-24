package org.yangxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author yangxin
 *
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class Zuul
{
    public static void main( String[] args )
    {
        SpringApplication.run(Zuul.class,args);

    }

}
