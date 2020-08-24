package com.joaopimentel.ShoppingCart.modules.controller;

import com.joaopimentel.ShoppingCart.configuration.utils.ApiResponse;
import com.joaopimentel.ShoppingCart.configuration.utils.ResourceNotFoundException;
import com.joaopimentel.ShoppingCart.modules.dto.ProductDto;
import com.joaopimentel.ShoppingCart.modules.dto.ProductRequest;
import com.joaopimentel.ShoppingCart.modules.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        ProductDto product = productService.getProductById(id);

        if(product != null) return product;
        else throw new ResourceNotFoundException("Product", "id", id);
    }

    @PostMapping()
    public ProductDto createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        String productDeleted = productService.deleteProductById(id);

        if(productDeleted != null) return ResponseEntity.ok(new ApiResponse(true , productDeleted));
        else throw new ResourceNotFoundException("Product", "id", id);
    }
}
