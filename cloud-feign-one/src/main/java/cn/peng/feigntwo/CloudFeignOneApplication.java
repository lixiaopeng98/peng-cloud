package cn.peng.feigntwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient  //注册eureka用的
@EnableFeignClients     //开启feign客户端
@EnableCircuitBreaker //开启断路器
public class CloudFeignOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFeignOneApplication.class,args);
    }
}
