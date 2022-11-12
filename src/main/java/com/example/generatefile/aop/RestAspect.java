package com.example.generatefile.aop;

import com.example.generatefile.dto.CsvDto;
import com.example.generatefile.dto.LogDto;
import com.example.generatefile.dto.TxtDto;
import com.example.generatefile.dto.UuidDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.logging.Logger;

@Aspect
@Component
public class RestAspect {

    @Pointcut("execution(* com.example.generatefile.restcontroller.*.*(..))")
    public void rest() {}

    @Around("rest()")
    public Object getLog(ProceedingJoinPoint point) {
        Logger logger = Logger.getLogger(RestAspect.class.getName());

        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();

        LogDto dto = new LogDto();
        dto.setUuid(UUID.randomUUID());
        dto.setStatus("OK");
        dto.setClassName(className);
        dto.setMethodName(methodName);
        Calendar calendar = new GregorianCalendar();
        dto.setTime(calendar.getTime());

        Object arg = args[0];
        if (arg.toString().startsWith("UuidDto")) {
            UuidDto uuidDto = (UuidDto) arg;
            arg = uuidDto.getCount();
        }
        else if (arg.toString().startsWith("CsvDto")) {
            CsvDto csvDto = (CsvDto) arg;
            arg = null;
        }
        else {
            TxtDto txtDto = (TxtDto) arg;
            arg = txtDto.getText();
        }

        dto.setArg(arg);
        dto.setMessage(dto.getTime() + ": Статус " + dto.getStatus() + " - Вызов метода " + methodName + " из класса " + className + " с параметром " + dto.getArg());

        logger.info(dto.getMessage());

        Object object = null;
        try {
            object = point.proceed();
        } catch (Throwable throwable) {
            dto.setStatus("ERROR");
            calendar = new GregorianCalendar();
            dto.setTime(calendar.getTime());
            dto.setMessage(throwable.getMessage());
            logger.info(dto.getTime() + ": Статус " + dto.getStatus() + " - Вызов метода " + methodName + " из класса " + className + " сообщение " + dto.getMessage());
        }
        return object;
    }
}
