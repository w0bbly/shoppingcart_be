package com.joaopimentel.ShoppingCart.modules.mappper;

import com.joaopimentel.ShoppingCart.configuration.EntityMapper;
import com.joaopimentel.ShoppingCart.modules.dto.UserDto;
import com.joaopimentel.ShoppingCart.modules.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapping extends EntityMapper<UserDto, User> {
}
