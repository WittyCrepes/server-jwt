package com.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.platform"})
@MapperScan(basePackages = {"com.platform"})
public class ServerJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerJwtApplication.class, args);
    }
}
