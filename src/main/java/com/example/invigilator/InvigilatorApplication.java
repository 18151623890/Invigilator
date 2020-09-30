package com.example.invigilator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.invigilator.mapper")
public class InvigilatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvigilatorApplication.class, args);
    }

}
