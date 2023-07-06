package com.hehetenya.phonecontacts.exception;

import com.hehetenya.phonecontacts.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentialsException(BadCredentialsException exception, ServletWebRequest servletWebRequest) {
        return new ResponseEntity<>(new ErrorResponseDTO(ZonedDateTime.now(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED.value(), exception.getMessage(), servletWebRequest.getRequest().getRequestURI()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException exception, ServletWebRequest servletWebRequest) {
        return new ResponseEntity<>(new ErrorResponseDTO(ZonedDateTime.now(), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value(), exception.getMessage(), servletWebRequest.getRequest().getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(ConstraintViolationException exception, ServletWebRequest servletWebRequest) {
        return new ResponseEntity<>(new ErrorResponseDTO(ZonedDateTime.now(), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value(), exception.getMessage(), servletWebRequest.getRequest().getRequestURI()), HttpStatus.BAD_REQUEST);
    }
}
