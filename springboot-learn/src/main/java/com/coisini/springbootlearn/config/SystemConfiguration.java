package com.coisini.springbootlearn.config;

import com.coisini.springbootlearn.sample.SystemInter;
import com.coisini.springbootlearn.sample.condition.LinuxCondition;
import com.coisini.springbootlearn.sample.condition.WindowsCondition;
import com.coisini.springbootlearn.sample.model.Linux;
import com.coisini.springbootlearn.sample.model.Windows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConfiguration {

    @Bean
    @Conditional(LinuxCondition.class)
    public SystemInter linux() {
        return new Linux();
    }

    @Bean
    @Conditional(WindowsCondition.class)
    public SystemInter windows() {
        return new Windows();
    }
}
