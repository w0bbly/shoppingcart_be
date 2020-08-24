package com.joaopimentel.ShoppingCart.modules.dto;

import com.joaopimentel.ShoppingCart.configuration.AuditModel;
import com.joaopimentel.ShoppingCart.configuration.utils.AuthProvider;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto extends AuditModel {
    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private Boolean emailVerified = false;
    private AuthProvider provider;
    private String providerId;
    private List<ShoppingListDto> lists = new ArrayList<>();
}
