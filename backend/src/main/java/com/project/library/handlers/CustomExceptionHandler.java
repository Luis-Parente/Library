package com.project.library.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.library.dto.CustomErrorDTO;
import com.project.library.exceptions.EntityNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        CustomErrorDTO errorDTO = new CustomErrorDTO(HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(errorDTO.status()).body(errorDTO);
    }  
}