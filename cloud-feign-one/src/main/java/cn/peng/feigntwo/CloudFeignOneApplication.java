package cn.peng.feigntwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableOAuth2Client
public class CloudFeignOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFeignOneApplication.class,args);
    }
}