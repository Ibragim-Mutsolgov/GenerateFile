package com.example.generatefile.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/generate_csv")
public class GenerateCSVController {

    @GetMapping
    public String getPage() {
        return "generate_csv";
    }

    @PostMapping
    public ResponseEntity<Resource> getFile() {
        return null;
    }
}
