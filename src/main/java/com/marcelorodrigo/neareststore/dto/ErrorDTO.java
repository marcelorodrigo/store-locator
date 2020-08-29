package com.marcelorodrigo.neareststore.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorDTO {

    private final LocalDateTime dateTime = LocalDateTime.now();
    private final String message;

    private ErrorDTO(String message) {
        this.message = message;
    }

    public static ErrorDTO of(Exception e) {
        return new ErrorDTO(e.getMessage());
    }
}
