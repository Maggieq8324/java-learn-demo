package com.coisini.springbootlearn.sample.model;

import com.coisini.springbootlearn.sample.SystemInter;

public class Linux implements SystemInter {

    @Override
    public String get() {
        return "System's Linux";
    }
}
