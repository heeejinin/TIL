package com.example.WebSample.dto;

import com.example.WebSample.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor // 생성자를 만들어줌
@Data // Getter, Setter을 생성해 줌
public class ErrorResponse {
    private ErrorCode errorCode;
    private String message;
}
