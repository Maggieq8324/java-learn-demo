package com.coisini.springbootlearn.sample.model;

import com.coisini.springbootlearn.sample.TestInter;

//@Component
public class TestOne implements TestInter {

    public TestOne () {
//        System.out.println("TestOne init");
    }

    @Override
    public String sayHello() {
        return "Hello TestOne";
    }
}
