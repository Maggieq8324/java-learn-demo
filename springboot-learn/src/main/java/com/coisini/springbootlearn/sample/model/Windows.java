package com.coisini.springbootlearn.sample.model;

import com.coisini.springbootlearn.sample.SystemInter;

public class Windows implements SystemInter {
    @Override
    public String get() {
        return "System's windows";
    }
}
