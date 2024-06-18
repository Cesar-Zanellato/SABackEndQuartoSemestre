package com.back.fortesupermercados.infra.erros;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(
        {EntityNotFoundException.class, NoSuchElementException.class}
    )
    public ResponseEntity treatNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity treatBadRequest(ConstraintViolationException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
