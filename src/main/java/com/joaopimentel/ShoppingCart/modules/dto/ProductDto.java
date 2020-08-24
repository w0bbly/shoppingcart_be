package com.joaopimentel.ShoppingCart.modules.dto;

import com.joaopimentel.ShoppingCart.configuration.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto extends AuditModel {
    private Long id;
    private String name;
    private String description;
}
