package com.example.HotelService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>>handleResourceException(ResourceNotFoundException ex){
    Map map = new HashMap();
    map.put("message", ex.getMessage());
    map.put("status", HttpStatus.NOT_FOUND);
    map.put("success", false);
    return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
