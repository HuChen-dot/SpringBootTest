package com.zeyu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan("com.zeyu.demo.mapper.*.*")
public class SpringBootTestApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootTestApplication.class, args);
        System.err.println("项目启动成功");
    }

}
