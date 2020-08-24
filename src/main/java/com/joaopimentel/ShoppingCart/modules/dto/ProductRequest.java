package com.joaopimentel.ShoppingCart.modules.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductRequest {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
