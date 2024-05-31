package com.iuroc;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.iuroc.util.MakeRes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MakeRes<String>> handleException(Exception e) {
        Throwable cause = e.getCause();
        return ResponseEntity
                .ok(new MakeRes<>(false, cause == null ? e.getMessage() : cause.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<MakeRes<String>> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        return ResponseEntity.ok(new MakeRes<>(false, e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MakeRes<String>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        return ResponseEntity.ok(new MakeRes<>(false, "请求体格式错误"));
    }
}
