package com.joaopimentel.ShoppingCart.modules.repository;

import com.joaopimentel.ShoppingCart.modules.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
}
