package com.scalar.productservice.advice;

import com.scalar.productservice.dto.ExectionDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ExectionDto> handleException(Exception ex){

        return new  ResponseEntity<ExectionDto>(new ExectionDto("Server Goes Down please try After some time"), HttpStatusCode.valueOf(405));

    }

}


