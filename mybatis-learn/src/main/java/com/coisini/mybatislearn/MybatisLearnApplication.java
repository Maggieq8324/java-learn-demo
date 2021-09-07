package com.coisini.mybatislearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.coisini.mybatislearn.mapper"}) // 扫描的mapper
public class MybatisLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisLearnApplication.class, args);
    }

}
