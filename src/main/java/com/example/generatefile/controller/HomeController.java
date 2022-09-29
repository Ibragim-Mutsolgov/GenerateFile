package com.example.generatefile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getPage() {
        return "redirect:/generate_txt";
    }
}
