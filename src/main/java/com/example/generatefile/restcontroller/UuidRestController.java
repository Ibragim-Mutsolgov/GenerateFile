package com.example.generatefile.restcontroller;

import com.example.generatefile.dto.UuidDto;
import com.example.generatefile.system_settings.SystemSettings;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/uuid")
@CrossOrigin(value = {"http://localhost:3000", "https://datafileweb.herokuapp.com/uuid"})
public class UuidRestController {

    @PostMapping
    public ResponseEntity<Resource> getFile(UuidDto dto) throws IOException {
        Long count = dto.getCount();
        if (count > 0) {
            StringBuilder uuidOfString = new StringBuilder();
            for (int i = 0; i < count; i++) {
                if (i > 0) uuidOfString.append("\n");
                uuidOfString.append(UUID.randomUUID());
            }
            return SystemSettings.getFile(uuidOfString);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }
}
