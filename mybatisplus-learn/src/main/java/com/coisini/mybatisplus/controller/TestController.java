package com.coisini.mybatisplus.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.coisini.mybatisplus.entity.Test;

import org.springframework.web.bind.annotation.RestController;

/**
* @author generator@xxx
* @since 2021-09-07
*/
@RestController
@RequestMapping("/controller/test")
public class TestController {

    @PostMapping("")
    public void create() {

    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }

    @GetMapping("/{id}")
    public Test get(@PathVariable(value = "id") Long id) {
        return null;
    }

}
