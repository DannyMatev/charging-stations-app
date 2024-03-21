package com.hubject.charging.controller.exception;

import com.hubject.charging.exception.ChargingStationAlreadyExists;
import com.hubject.charging.exception.ChargingStationNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ChargingStationNotFoundException.class})
  protected ResponseEntity<Object> handleConflict(
      ChargingStationNotFoundException ex, WebRequest request) {
    String responseBody = ex.getMessage();
    return handleExceptionInternal(
        ex, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {ChargingStationAlreadyExists.class})
  protected ResponseEntity<Object> handleConflict(
      ChargingStationAlreadyExists ex, WebRequest request) {
    String responseBody = ex.getMessage();
    return handleExceptionInternal(
        ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
