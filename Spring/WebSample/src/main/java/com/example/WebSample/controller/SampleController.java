package com.example.WebSample.controller;

import com.example.WebSample.dto.ErrorResponse;
import com.example.WebSample.exception.ErrorCode;
import com.example.WebSample.exception.WebSampleException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestController
public class SampleController {

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException, SQLIntegrityConstraintViolationException {
        log.info("Get some order: " + id);

        if ("500".equals(id)) {
            throw new WebSampleException(ErrorCode.TOO_BIG_ID_ERROR, "500 is too big orderId.");
        }
        if ("3".equals(id)) {
            throw new WebSampleException(ErrorCode.TOO_SAMLL_ID_ERROR, "3 is too small orderId.");
        }

        if ("4".equals(id)) {
            throw new SQLIntegrityConstraintViolationException("Duplicated insertion was tried.");
        }

        return "OrderId:" + id + ", orderAmount: 1000";
    }

    // IllegalAccessException을 처리하는 핸들러
    // IllegalAccessException은 Exception의 자식 클래스(RuntimeException, IllegalAccessException 등)
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorResponse> handelIllegalAccessException(IllegalAccessException e) {
        log.error("IllegalAccessException is occurred : ", e);

        // dto 패키지의 ErrorResponse
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR, "IllegalAccessException is occurred."));
    }

    // Exception이 정확히 지정된 Handler
    // 정확히 지정된 WebSampleException을 처리하는 핸들러
    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handelWebSampleException(WebSampleException e) {
        log.error("WebSampleException is occurred : ", e);

        return ResponseEntity
                .status(HttpStatus.INSUFFICIENT_STORAGE)
                .body(new ErrorResponse(e.getErrorCode(), "IllegalAccesssException is occurred."));
    }

    // 모든 예외를 처리하는 핸들러 (catch-all)
    // 특정 에러를 지정하지 않는 가장 최후의 보루로 사용하는 예외처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handelException(Exception e) {
        log.error("Exception is occurred : ", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "Exception is occurred."));
    }

    @DeleteMapping("/order/{orderId}")
    public String deleteOrder(@PathVariable("orderId") String id) {
        log.info("Delete some order: " + id);
        return "Delete orderId:" + id;
    }

    @GetMapping("/order")
    public String getOrderWithRequestParam(@RequestParam("orderId") String id, @RequestParam("orderAmount") Integer amount) {
        log.info("Get order: " + id + ", amount : " + amount);
        return "OrderId: " + id + ", orderAmount: " + amount;
    }

    @PostMapping("/order")
    public String createOrder(@RequestBody CreateOrderRequest createOrderRequest, @RequestHeader String userAccountId) {
        log.info("Create order: " + createOrderRequest + ", amount : " + userAccountId);
        return "OrderId: " + createOrderRequest.getOrderId() + ", orderAmount: " + createOrderRequest.getOrderAmount();
    }

    @PutMapping("/order")
    public String createOrder() {
        log.info("Create order");
        return "order created -> orderId: 1, orderAmount: 1000";
    }

    @Data // java Bean 객체로 생성해 주는 어노테이션
    // Getter, Setter도 자동으로 생성됨
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;
    }
}

