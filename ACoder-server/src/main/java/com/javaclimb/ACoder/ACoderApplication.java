package com.javaclimb.ACoder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.javaclimb.ACoder.dao")
public class ACoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ACoderApplication.class, args);
    }

}
