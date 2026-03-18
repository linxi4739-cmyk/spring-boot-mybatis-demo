package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class Demo2Application {
    public static void main(String[] args) {
        // 删掉了 System.setProperty，保持主类整洁
        SpringApplication.run(Demo2Application.class, args);
    }
}