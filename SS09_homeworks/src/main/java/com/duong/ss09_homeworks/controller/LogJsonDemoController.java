package com.duong.ss09_homeworks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/json-log")
public class LogJsonDemoController {

    private static final Logger logger = LoggerFactory.getLogger(LogJsonDemoController.class);

    @PostMapping("/login")
    public String login(@RequestParam String username) {
        Map<String, Object> logData = new HashMap<>();
        logData.put("event", "user_login");
        logData.put("username", username);
        logData.put("status", "success");

        logger.info("Login event: {}", logData);
        return "Đăng nhập thành công cho user: " + username;
    }
}