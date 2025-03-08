package com.blog.blog.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFound e) {
        String msg = e.getMessage();
        ApiResponse res = new ApiResponse(msg, false);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.NOT_FOUND);
    }
}
