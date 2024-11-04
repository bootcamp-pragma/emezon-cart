package com.emezon.cart.infra.inbound.rest.controllers;

import com.emezon.cart.infra.inbound.rest.constants.RestApiConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(RestApiConstants.API_CART)
public class CartController {

    @GetMapping("/test")
    public String test() {
        return "Hello from CartController";
    }

}
