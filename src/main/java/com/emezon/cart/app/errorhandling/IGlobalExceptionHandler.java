package com.emezon.cart.app.errorhandling;

public interface IGlobalExceptionHandler<T> {

    Object handleGeneralException(Exception ex, T request);

}