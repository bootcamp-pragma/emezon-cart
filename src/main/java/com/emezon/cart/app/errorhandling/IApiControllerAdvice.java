package com.emezon.cart.app.errorhandling;

public interface IApiControllerAdvice<T> {

    Object handleMethodArgumentNotValidException(Exception ex, T request);

    Object handleConstraintViolationException(Exception ex, T request);

    Object handleInvalidFormatException(Exception ex, T request);

    Object handleIllegalArgumentException(Exception ex, T request);

    Object handleAuthorizationDeniedException(Exception ex, T request);

    Object handleDateTimeParseException(Exception ex, T request);

    Object handleMalformedJwtException(Exception ex, T request);

}
