package cn.peng.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAuthServiceApplication.class,args);
    }
}
