package com.guuh.transaction_notification_service.infrastructure.handler;

import com.guuh.transaction_notification_service.infrastructure.exception.EmailSendException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EmailSendException.class)
    private ResponseEntity<RestErrorMessage> EmailSendHandler(EmailSendException e) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }
}
