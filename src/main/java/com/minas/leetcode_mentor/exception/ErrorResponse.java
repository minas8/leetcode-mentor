package com.minas.leetcode_mentor.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;

// DTO
@Data
@AllArgsConstructor
public class ErrorResponse {
    private final String error;
    private final String message;
    private final HttpStatus status;
    private final Instant timestamp;
}
