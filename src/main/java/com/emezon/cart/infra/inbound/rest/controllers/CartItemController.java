package com.emezon.cart.infra.inbound.rest.controllers;

import com.emezon.cart.app.dtos.CartItemDTO;
import com.emezon.cart.app.dtos.CreateCartItemDTO;
import com.emezon.cart.app.dtos.RemoveQuantityDTO;
import com.emezon.cart.app.handlers.ICartItemHandler;
import com.emezon.cart.domain.utils.PaginatedResponse;
import com.emezon.cart.infra.inbound.rest.constants.PaginatedConstants;
import com.emezon.cart.infra.inbound.rest.constants.RestApiConstants;
import com.emezon.cart.infra.inbound.rest.utils.ValidPageableRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(RestApiConstants.API_CART_ITEM)
@RequiredArgsConstructor
public class CartItemController {

    private final ICartItemHandler cartItemHandler;

    @GetMapping
    @Parameters({
            @Parameter(name = PaginatedConstants.PARAM_PAGE, description = PaginatedConstants.PARAM_PAGE_DESC, example = PaginatedConstants.PARAM_PAGE_EXAMPLE),
            @Parameter(name = PaginatedConstants.PARAM_SIZE, description = PaginatedConstants.PARAM_SIZE_DESC, example = PaginatedConstants.PARAM_SIZE_EXAMPLE),
            @Parameter(name = PaginatedConstants.PARAM_SORT, description = PaginatedConstants.PARAM_SORT_DESC,
                    array = @ArraySchema(schema = @Schema(type = "string", example = PaginatedConstants.PARAM_SORT_EXAMPLE)))
    })
    public ResponseEntity<PaginatedResponse<CartItemDTO>> getAllCartItems(
            @Parameter(hidden = true) @RequestParam @ValidPageableRequest(target = CartItemDTO.class) @Valid
            MultiValueMap<String, String> queryParams
    ) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> createCartItem(@RequestBody @Valid CreateCartItemDTO createCartItemDTO) {
        URI location = URI.create(RestApiConstants.API_CART_ITEM);
        CartItemDTO createdCartItem = cartItemHandler.createCartItem(createCartItemDTO);
        return ResponseEntity.created(location).body(createdCartItem);
    }

    @PatchMapping("/remove-quantity/{id}")
    public ResponseEntity<CartItemDTO> addQuantity(
            @PathVariable String id, @RequestBody @Valid RemoveQuantityDTO removeQuantityDTO) {
        CartItemDTO updatedCartItem = cartItemHandler.removeQuantity(id, removeQuantityDTO.getQuantity());
        return ResponseEntity.ok(updatedCartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable String id) {
        cartItemHandler.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }

}
