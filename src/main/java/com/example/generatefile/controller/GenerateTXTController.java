package com.example.generatefile.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class GenerateTXTController {

    @GetMapping
    public String getPage() {
        return "generate_txt";
    }

    @PostMapping
    public ResponseEntity<Resource> getFile(@RequestParam(name = "text") String text) throws IOException {
        if(text != null && !text.isEmpty()) {
            File file = new File("result.csv");
            FileOutputStream outputStream = new FileOutputStream(file, true);
            outputStream.write(text.getBytes());
            outputStream.flush();
            outputStream.close();
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
