package com.emezon.cart.infra.inbound.rest.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PageableRequestValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPageableRequest {
    String message() default "Some of the values of the sort parameter do not comply with the format.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] allowedParams() default  {};
    String[] allowedSortFormats() default {};
    Class<?> target() default Void.class;
}
