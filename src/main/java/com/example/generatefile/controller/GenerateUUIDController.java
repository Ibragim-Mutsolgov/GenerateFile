package com.example.generatefile.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.UUID;

@Controller
@RequestMapping("/generate_uuid")
public class GenerateUUIDController {

    @GetMapping
    public String getPage() {
        return "generate_uuid";
    }

    @PostMapping
    public ResponseEntity<Resource> getFile(@RequestParam(name = "count") Number count) throws IOException {
        if (count.longValue() > 0) {
            File file = new File("result.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            StringBuilder uuidOfString = new StringBuilder();
            for (int i = 0; i < count.longValue(); i++) {
                if (i > 0) uuidOfString.append("\n");
                uuidOfString.append(UUID.randomUUID());
            }
            fileOutputStream.write(uuidOfString.toString().getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(inputStreamResource);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }
}
