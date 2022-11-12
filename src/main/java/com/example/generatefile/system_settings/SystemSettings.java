package com.example.generatefile.system_settings;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;

public class SystemSettings {

    public static ResponseEntity<Resource> getFile(StringBuilder builder) throws IOException {
        File file = new File("result.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(builder.toString().getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=result.txt");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }
}
