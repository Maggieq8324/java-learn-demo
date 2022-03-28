package com.coisini.dynamic.controller;

import com.coisini.dynamic.config.DynamicMailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Value("${coisini.mail}")
    private String mail;

    @GetMapping("/getMail")
    public String getMail() {
        log.info("getMail: " + mail);
        return mail;
    }

    @GetMapping("/getDynamicMail")
    public String getDynamicMail() {
        String dynamicMail = DynamicMailConfig.getInstance().getDynamicMail();
        log.info("getDynamicMail: " + dynamicMail);
        return dynamicMail;
    }

}
