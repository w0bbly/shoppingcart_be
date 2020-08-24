package com.joaopimentel.ShoppingCart.modules.mappper;

import com.joaopimentel.ShoppingCart.configuration.EntityMapper;
import com.joaopimentel.ShoppingCart.modules.dto.ShoppingListDto;
import com.joaopimentel.ShoppingCart.modules.entity.ShoppingList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingListMapping extends EntityMapper<ShoppingListDto, ShoppingList> {
}
