package com.joaopimentel.ShoppingCart.modules.repository;

import com.joaopimentel.ShoppingCart.modules.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
