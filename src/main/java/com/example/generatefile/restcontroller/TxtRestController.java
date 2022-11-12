package com.example.generatefile.restcontroller;

import com.example.generatefile.dto.TxtDto;
import com.example.generatefile.system_settings.SystemSettings;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/txt")
@CrossOrigin(value = {"http://localhost:3000", "https://datafileweb.herokuapp.com/uuid"})
public class TxtRestController {

    @PostMapping
    public ResponseEntity<Resource> getFile(TxtDto dto) throws IOException {
        String text = dto.getText();
        if(text != null && !text.isEmpty()) {
            return SystemSettings.getFile(new StringBuilder(text));
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
