package com.dxc.ddc.springboot.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dxc.ddc.springboot.data.GeneralContentResult;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public GeneralContentResult<String> jsonErrorHandler(HttpServletRequest request, Exception e) {
        log.error("Handler Global Error", e);
        GeneralContentResult<String> response = new GeneralContentResult<String>();
        response.setResultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
        response.setDetailDescription(e.getMessage());
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public GeneralContentResult<String> jsonErrorHandlerForValidation(HttpServletRequest request, Exception e) {
        log.error("Handler Validation Error", e);
        GeneralContentResult<String> response = new GeneralContentResult<String>();
        response.setResultCode(String.valueOf(HttpStatus.BAD_REQUEST));
        response.setDetailDescription(e.getMessage());
        return response;
    }
}
