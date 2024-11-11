package com.emezon.cart.infra.inbound.rest.utils;

import com.emezon.cart.domain.constants.PaginatedResponseConstraints;
import com.emezon.cart.domain.constants.PaginatedResponseErrorMessages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.util.*;


public class PageableRequestValidator implements ConstraintValidator<ValidPageableRequest, MultiValueMap<String, String>> {

    private Set<String> allowedParams;
    private Set<String> allowedSortFormats;
    private List<String> targetProperties;

    @Override
    public void initialize(ValidPageableRequest constraintAnnotation) {
        if (constraintAnnotation.allowedParams().length > 0) {
            this.allowedParams = new HashSet<>(Arrays.asList(constraintAnnotation.allowedParams()));
        } else {
            this.allowedParams = new HashSet<>(PaginatedResponseConstraints.ALLOWED_PARAMS);
        }
        if (constraintAnnotation.allowedSortFormats().length > 0) {
            this.allowedSortFormats = new HashSet<>(Arrays.asList(constraintAnnotation.allowedSortFormats()));
        } else {
            this.allowedSortFormats = new HashSet<>(List.of(PaginatedResponseConstraints.VALID_SORT_FORMAT));
        }
        Class<?> target = constraintAnnotation.target();
        if (!Void.class.equals(target)) {
            Field[] fields = target.getDeclaredFields();
            this.targetProperties = Arrays.stream(fields).map(Field::getName).toList();
        } else {
            this.targetProperties = List.of();
        }
    }

    @Override
    public boolean isValid(MultiValueMap<String, String> s, ConstraintValidatorContext context) {
        boolean error = false;
        context.disableDefaultConstraintViolation();
        for (Map.Entry<String, List<String>> entry : s.entrySet()) {
            String key = entry.getKey();
            if (!allowedParams.contains(key)) {
                context.buildConstraintViolationWithTemplate("Unknown parameter: " + key).addConstraintViolation();
                error = true;
            }
            if (key.equals("sort")) {
                for (String value : s.get(key)) {
                    boolean valid = false;
                    for (String format : allowedSortFormats) {
                        if (value.matches(format)) {
                            valid = true;
                            break;
                        }
                    }
                    if (!valid) {
                        error = true;
                        context.buildConstraintViolationWithTemplate(PaginatedResponseErrorMessages.SORT_PARAM_INVALID)
                                .addConstraintViolation();
                    }
                    List<String> parts = Arrays.asList(value.split(","));
                    if (!targetProperties.isEmpty() && !targetProperties.contains(parts.get(0))) {
                        error = true;
                        context.buildConstraintViolationWithTemplate(String.format("No property named '%s' found", parts.get(0)))
                                .addConstraintViolation();
                    }
                }
            } else {
                if (s.get(key).size() != 1) {
                    context.buildConstraintViolationWithTemplate("Parameter " + key + " must have exactly one value").addConstraintViolation();
                    error = true;
                }
                if (key.equals("page")) {
                    try {
                        int page = Integer.parseInt(Objects.requireNonNull(s.getFirst(key)));
                        if (page < PaginatedResponseConstraints.PAGE_NUMBER_MIN) {
                            context.buildConstraintViolationWithTemplate(PaginatedResponseErrorMessages.PAGE_NUMBER_INVALID)
                                    .addConstraintViolation();
                            error = true;
                        }
                    } catch (NumberFormatException e) {
                        context.buildConstraintViolationWithTemplate("Page number must be an integer")
                                .addConstraintViolation();
                        error = true;
                    }
                } else if (key.equals("size")) {
                    try {
                        int size = Integer.parseInt(Objects.requireNonNull(s.getFirst(key)));
                        if (size < PaginatedResponseConstraints.PAGE_SIZE_MIN) {
                            context.buildConstraintViolationWithTemplate(PaginatedResponseErrorMessages.PAGE_SIZE_INVALID)
                                    .addConstraintViolation();
                            error = true;
                        }
                    } catch (NumberFormatException e) {
                        context.buildConstraintViolationWithTemplate("Page size must be an integer")
                                .addConstraintViolation();
                        error = true;
                    }
                }
            }
        }

        return !error;
    }
}
