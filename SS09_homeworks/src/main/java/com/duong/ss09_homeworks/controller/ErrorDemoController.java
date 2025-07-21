package com.duong.ss09_homeworks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error-demo")
public class ErrorDemoController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorDemoController.class);

    @GetMapping("/divide")
    public String divide(@RequestParam int a, @RequestParam int b) {
        try {
            int result = a / b;
            return "Kết quả: " + result;
        } catch (ArithmeticException ex) {
            logger.error("Lỗi chia cho 0: a={}, b={}", a, b, ex);
            return "Không thể chia cho 0!";
        } catch (Exception ex) {
            logger.error("Lỗi không xác định: {}", ex.getMessage(), ex);
            return "Đã xảy ra lỗi!";
        }
    }
}