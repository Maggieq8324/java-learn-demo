package com.coisini.springbootlearn.config;

import com.coisini.springbootlearn.sample.TestInter;
import com.coisini.springbootlearn.sample.model.TestOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public TestInter testOne() {
        return new TestOne();
    }

//    @Bean
//    public TestInter testTwo() {
//        return new TestTwo();
//    }
}
