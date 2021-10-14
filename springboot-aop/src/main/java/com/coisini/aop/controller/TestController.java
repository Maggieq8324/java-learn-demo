package com.coisini.aop.controller;

import com.coisini.aop.auth.annotation.CheckLogin;
import com.coisini.aop.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * 获取Token
     * 此处模拟用户登录获取token
     * @param id
     * @return
     */
    @GetMapping(value = "/get")
    public String getToken(@RequestParam Long id) {
        // TODO 业务处理，用户,验证码校验。。。
        return JwtUtil.makeToken(id);
    }

    /**
     * AOP校验请求头中的Token
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/test")
    public String testCheckLogin() {
        // TODO 业务
        return "Token验证通过";
    }

}
