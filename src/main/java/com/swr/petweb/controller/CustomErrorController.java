package com.swr.petweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustomErrorController {
    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        return "에러 페이지입니다";
    }
}
