package com.inditex.ivan.duran.infrastructure.adapter.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorRestController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        // Aquí puedes personalizar la respuesta de error
        String errorMessage = "Error 404 - Recurso no encontrado";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
