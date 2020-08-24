package com.joaopimentel.ShoppingCart.modules.dto;

import com.joaopimentel.ShoppingCart.configuration.AuditModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShoppingListDto extends AuditModel {
    private Long id;
    private List<ProductDto> products = new ArrayList<>();
}
