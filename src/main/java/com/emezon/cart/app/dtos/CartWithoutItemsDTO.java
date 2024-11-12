package com.emezon.cart.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartWithoutItemsDTO {

    private String id;
    private String clientId;
    private double total;
    private int status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
