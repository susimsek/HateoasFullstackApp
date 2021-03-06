package io.github.susimsek.hateoasbackend.exception;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.github.susimsek.hateoasbackend.constant.ErrorCodeConstants.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    final MessageSource messageSource;

    // 400
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        final Map<String, String> errorMap = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse(""),
                        (oldValue, newValue) -> newValue));
        return new ResponseEntity<>(errorMap, headers, HttpStatus.BAD_REQUEST);
    }

    // 400
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        final Map<String, String> errorMap = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse(""),
                        (oldValue, newValue) -> newValue));
        return new ResponseEntity<>(errorMap, headers, HttpStatus.BAD_REQUEST);
    }

    // 400  request body is missing or it is unreadable
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), headers, HttpStatus.BAD_REQUEST);
    }

    // 400 uri syntax exception
    @ExceptionHandler(URISyntaxException.class)
    public ResponseEntity<Object> handleUriSyntaxException(
            URISyntaxException ex,
            WebRequest request) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    // 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFound(
            ResourceNotFoundException ex, Locale locale) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
        final Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", errorMessage);
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex,
                                            WebRequest request,
                                            Locale locale) {
        String errorMessage = messageSource.getMessage(INTERNAL_SERVER_ERROR, null, locale);
        final Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", errorMessage);
        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
