package com.example.WebSample.exception;

import com.example.WebSample.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorResponse> handelIllegalAccessException(IllegalAccessException e) {
        log.error("IllegalAccessException is occurred : ", e);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR, "IllegalAccessException is occurred."));
    }

    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handelWebSampleException(WebSampleException e) {
        log.error("WebSampleException is occurred : ", e);

        return ResponseEntity
                .status(HttpStatus.INSUFFICIENT_STORAGE)
                .body(new ErrorResponse(e.getErrorCode(), "IllegalAccesssException is occurred."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handelException(Exception e) {
        log.error("Exception is occurred : ", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "Exception is occurred."));
    }
}
