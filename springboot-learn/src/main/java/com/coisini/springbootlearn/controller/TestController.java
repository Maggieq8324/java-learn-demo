package com.coisini.springbootlearn.controller;

import com.coisini.springbootlearn.manager.rocketmq.ProducerSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

//    @Autowired
//    private ProducerSchedule producerSchedule;
//
//    @GetMapping("/push")
//    public void pushMessageToMQ() throws Exception {
//        producerSchedule.send("Topic", "Coisini");
//    }
}
