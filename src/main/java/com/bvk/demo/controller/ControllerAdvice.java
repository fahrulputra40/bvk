package com.bvk.demo.controller;

import com.bvk.demo.exception.ServiceException;
import com.bvk.demo.model.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> serviceException(ServiceException serviceException){
        return composeResponse(serviceException);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> serviceException(Exception serviceException){
        return composeResponse(serviceException);
    }

    private ResponseEntity<BaseResponse> composeResponse(Exception e){
        BaseResponse baseResponse = new BaseResponse<>();
        BaseResponse.ErrorResponse errorResponse = new BaseResponse.ErrorResponse();
        errorResponse.setReason(e.getMessage());
        baseResponse.setError(errorResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
    }
}
