package com.base.datamanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.base.datamanage.mapper")
public class DataManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataManageApplication.class, args);
    }

}
