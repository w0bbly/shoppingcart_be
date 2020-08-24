package com.joaopimentel.ShoppingCart.modules.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ListRequest {
    @NotNull
    private Long userId;
}
