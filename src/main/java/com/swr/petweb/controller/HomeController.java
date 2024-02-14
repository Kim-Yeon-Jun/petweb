package com.swr.petweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins="https://teamswr.store")
@Controller
public class HomeController {

    @GetMapping("/")
    public String homepage(Model model){
        model.addAttribute("message","홈페이지");
        return "homepage";
    }
}
