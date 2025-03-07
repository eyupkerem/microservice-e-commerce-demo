package com.malkoc.costumerservice.handler;

import com.malkoc.costumerservice.exception.CostumerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CostumerNotFoundException.class)
    public ResponseEntity<String> handle(CostumerNotFoundException exp){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exp.getMsg());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp){

        var errors= new HashMap<String,String>();

        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    if (error instanceof FieldError fieldError) {
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                    }
                });

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(errors));
    }




}
