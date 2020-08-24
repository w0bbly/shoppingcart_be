package com.joaopimentel.ShoppingCart.modules.mappper;

import com.joaopimentel.ShoppingCart.configuration.EntityMapper;
import com.joaopimentel.ShoppingCart.modules.dto.ProductDto;
import com.joaopimentel.ShoppingCart.modules.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapping extends EntityMapper<ProductDto, Product> {
}
