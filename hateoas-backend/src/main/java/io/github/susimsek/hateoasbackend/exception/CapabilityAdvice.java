package io.github.susimsek.hateoasbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CapabilityAdvice {

    @ExceptionHandler(CapabilityNotFoundException.class)
    public final ResponseEntity<CapabilityNotFoundResponse> capabilityNotFoundResponseResponseEntity(CapabilityNotFoundException ex) {
        var response = new CapabilityNotFoundResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
