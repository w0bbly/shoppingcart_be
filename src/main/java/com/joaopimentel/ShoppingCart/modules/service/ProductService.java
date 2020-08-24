package com.joaopimentel.ShoppingCart.modules.service;

import com.joaopimentel.ShoppingCart.modules.dto.ProductDto;
import com.joaopimentel.ShoppingCart.modules.dto.ProductRequest;
import com.joaopimentel.ShoppingCart.modules.entity.Product;
import com.joaopimentel.ShoppingCart.modules.mappper.ProductMapping;
import com.joaopimentel.ShoppingCart.modules.repository.ProductRepository;
import com.joaopimentel.ShoppingCart.modules.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapping productMapping;
    private final ShoppingListRepository shoppingListRepository;

    public ProductService(ProductRepository productRepository, ProductMapping productMapping, ShoppingListRepository shoppingListRepository) {
        this.productRepository = productRepository;
        this.productMapping = productMapping;
        this.shoppingListRepository = shoppingListRepository;
    }

    public ProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapping::toDto).orElse(null);
    }

    public ProductDto createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product = productRepository.save(product);
        return productMapping.toDto(product);
    }

    public String deleteProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()) {
            product.get().getShoppingList().forEach(shoppingList -> shoppingList.getProducts().removeIf(pro -> pro.getId().equals(id)));
            productRepository.deleteById(id);
            return "Product with id " + id + " successfully deleted!";
        } else return null;
    }
}
