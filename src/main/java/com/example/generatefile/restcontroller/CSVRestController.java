package com.example.generatefile.restcontroller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csv")
@CrossOrigin(value = {"http://localhost:3000", "https://datafilebackend.herokuapp.com/csv"})
public class CSVRestController {

    @PostMapping
    public ResponseEntity<Resource> getFile() {
        return null;
    }
}
