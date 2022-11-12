package com.example.generatefile.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class LogDto {

    private UUID uuid;

    private String status;

    private String className;

    private String methodName;

    private Date time;

    private Object arg;

    private String message;
}
