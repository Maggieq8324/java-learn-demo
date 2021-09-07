package com.coisini.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Coisini
 */
@SpringBootApplication
// @ComponentScan包扫描
//@ComponentScan(value = "com.coisini",
//                excludeFilters = {@ComponentScan.Filter(
//                        type = FilterType.ASSIGNABLE_TYPE,
//                        classes = TestOneController.class)})
public class SpringLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
    }

}
