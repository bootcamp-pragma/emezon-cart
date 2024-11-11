package com.emezon.cart.infra.outbound.feign.utils;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new RuntimeException(String.format("Resource '%s' not found", methodKey));
        }
        if (response.status() == 400) {
            return new RuntimeException(String.format("Bad request for '%s'", methodKey));
        }
        if (response.status() == 401) {
            return new RuntimeException(String.format("Unauthorized request for '%s'", methodKey));
        }
        return new Exception("Error");
    }


}
