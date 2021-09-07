package com.coisini.springbootlearn.sample.model;

import com.coisini.springbootlearn.sample.TestInter;

//@Component
public class TestTwo implements TestInter {

    public TestTwo () {
        System.out.println("TestTwo init");
    }

    @Override
    public String sayHello() {
        return "Hello TestTwo";
    }
}
