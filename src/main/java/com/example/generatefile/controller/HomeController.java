package com.example.generatefile.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
@AllArgsConstructor
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping
    public ResponseEntity<Resource> generate(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "column") String column) throws IOException {
        if(name != null && !name.isEmpty()
                && column != null && !column.isEmpty()) {
            File file = new File(name + ".csv");
            FileOutputStream outputStream = new FileOutputStream(file, true);
            outputStream.write(column.getBytes());
            outputStream.flush();
            outputStream.close();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name + ".csv");
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
