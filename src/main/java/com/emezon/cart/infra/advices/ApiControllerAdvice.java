package com.emezon.cart.infra.advices;

import com.emezon.cart.app.errorhandling.IApiControllerAdvice;
import com.emezon.cart.domain.utils.ExceptionResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@ControllerAdvice
@Order(10)
public class ApiControllerAdvice implements IApiControllerAdvice<WebRequest> {

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
        List<String> errorsMessages = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).toList();
        String messages = String.join(",\n ", errorsMessages);
        ExceptionResponse response = new ExceptionResponse(
                messages,
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HandlerMethodValidationException e = (HandlerMethodValidationException) ex;
        List<String> errorMessages = e.getAllValidationResults().stream().map(ParameterValidationResult::getResolvableErrors)
                .flatMap(List::stream).map(MessageSourceResolvable::getDefaultMessage).toList();
        String message = String.join(",\n ", errorMessages);
        ExceptionResponse response = new ExceptionResponse(
                message,
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidFormatException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Throwable cause = ex.getCause();
        String message = ex.getMessage();
        if (cause instanceof InvalidFormatException invalidFormatEx) {
            if (invalidFormatEx.getTargetType() == LocalDate.class &&
                    invalidFormatEx.getPath() != null &&
                    !invalidFormatEx.getPath().isEmpty() &&
                    "birthdate".equals(invalidFormatEx.getPath().get(0).getFieldName())) {
                message = "Invalid date format. Please use the format yyyy-MM-dd";
            }
            if (invalidFormatEx.getTargetType() == LocalDateTime.class &&
                    invalidFormatEx.getPath() != null &&
                    !invalidFormatEx.getPath().isEmpty() &&
                    "restockDate".equals(invalidFormatEx.getPath().get(0).getFieldName())) {
                message = "Invalid date format. Please use the format yyyy-MM-dd'T'HH:mm:ss";
            }
        }
        ExceptionResponse response = new ExceptionResponse(
                message,
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAuthorizationDeniedException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(DateTimeParseException.class)
    public Object handleDateTimeParseException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                "Invalid date format",
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

    @Override
    @ExceptionHandler(MalformedJwtException.class)
    public Object handleMalformedJwtException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false),
                status.value());
        return new ResponseEntity<>(response, status);
    }

}
