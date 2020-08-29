package com.marcelorodrigo.neareststore.controller;

import com.marcelorodrigo.neareststore.dto.ErrorDTO;
import com.marcelorodrigo.neareststore.exception.InvalidCoordinateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(InvalidCoordinateException.class)
    public ResponseEntity<ErrorDTO> handleInvalidCoordinateException(InvalidCoordinateException e) {
        log.debug("Invalid coordinates received.", e);
        return ResponseEntity.badRequest().body(ErrorDTO.of(e));
    }
}