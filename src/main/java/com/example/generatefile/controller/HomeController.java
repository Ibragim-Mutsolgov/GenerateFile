package com.example.generatefile.controller;

import org.apache.coyote.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping
    public ResponseEntity<Resource> generate(@RequestParam(name = "name") String name) throws IOException {
        if(name != null && !name.equals("")) {
            File file = new File("result.txt");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(name.getBytes());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
