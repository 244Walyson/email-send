package com.waly.emailsend.controllers.ExceptionHandlers;

import com.waly.emailsend.dto.CustonError;
import com.waly.emailsend.dto.CustonErrorValidation;
import com.waly.emailsend.service.Exceptions.DatabaseException;
import com.waly.emailsend.service.Exceptions.EmailException;
import com.waly.emailsend.service.Exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustonError> databaseException(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustonError error = new CustonError();
        error.setPath(request.getRequestURI());
        error.setTimestamp(Instant.now());
        error.setError("Database exception");
        error.setMessage(e.getMessage());
        return ResponseEntity.status(status.value()).body(error);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustonError> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustonError custonError = new CustonError();

        custonError.setPath(request.getRequestURI());
        custonError.setTimestamp(Instant.now());
        custonError.setMessage(e.getMessage());
        custonError.setError("not found");
        custonError.setStatus(status.value());
        return ResponseEntity.status(status.value()).body(custonError);
    }
}
