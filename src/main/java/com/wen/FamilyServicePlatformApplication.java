package com.wen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wen.mapper")
public class FamilyServicePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyServicePlatformApplication.class, args);
    }

}
