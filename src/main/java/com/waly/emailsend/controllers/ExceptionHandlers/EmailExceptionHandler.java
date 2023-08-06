package com.waly.emailsend.controllers.ExceptionHandlers;

import com.waly.emailsend.dto.CustonError;
import com.waly.emailsend.dto.CustonErrorValidation;
import com.waly.emailsend.service.Exceptions.EmailException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class EmailExceptionHandler {

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<CustonError> emailException(EmailException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustonError error = new CustonError();
        error.setPath(request.getRequestURI());
        error.setTimestamp(Instant.now());
        error.setError("Falha ao enviar email");
        error.setMessage(e.getMessage());
        return ResponseEntity.status(status.value()).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustonErrorValidation> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        CustonErrorValidation err = new CustonErrorValidation();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Validation exception");
        err.setPath(request.getRequestURI());
        err.setError(e.getMessage());

        for (FieldError f : e.getBindingResult().getFieldErrors()){
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);

    }
}
