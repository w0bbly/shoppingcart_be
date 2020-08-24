package com.joaopimentel.ShoppingCart.modules.service;

import com.joaopimentel.ShoppingCart.modules.dto.ListRequest;
import com.joaopimentel.ShoppingCart.modules.dto.ShoppingListDto;
import com.joaopimentel.ShoppingCart.modules.entity.Product;
import com.joaopimentel.ShoppingCart.modules.entity.ShoppingList;
import com.joaopimentel.ShoppingCart.modules.entity.User;
import com.joaopimentel.ShoppingCart.modules.mappper.ShoppingListMapping;
import com.joaopimentel.ShoppingCart.modules.repository.ProductRepository;
import com.joaopimentel.ShoppingCart.modules.repository.ShoppingListRepository;
import com.joaopimentel.ShoppingCart.modules.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListMapping shoppingListMapping;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, ShoppingListMapping shoppingListMapping, ProductRepository productRepository, UserRepository userRepository, ProductRepository productRepository1) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListMapping = shoppingListMapping;
        this.userRepository = userRepository;
        this.productRepository = productRepository1;
    }

    public ShoppingListDto getShoppingListById(Long id) {
        Optional<ShoppingList> list = shoppingListRepository.findById(id);
        return list.map(shoppingListMapping::toDto).orElse(null);
    }

    public ShoppingListDto createShoppingList(ListRequest listRequest) {
        Optional<User> user = userRepository.findById(listRequest.getUserId());

        if(user.isPresent()) {
            ShoppingList list = new ShoppingList();
            list.setProducts(new HashSet<>());
            list.setUser(user.get());
            list = shoppingListRepository.save(list);
            return shoppingListMapping.toDto(list);
        } else {
            return null;
        }
    }

    public String deleteShoppingListById(Long id) {
        Optional<ShoppingList> list = shoppingListRepository.findById(id);

        if(list.isPresent()) {
            shoppingListRepository.deleteById(id);
            return "List with id " + id + " successfully deleted!";
        } else return null;
    }

    public String deleteAllProductsOfList(Long id) {
        Optional<ShoppingList> list = shoppingListRepository.findById(id);

        if(list.isPresent()) {
            if(list.get().getProducts().removeAll(list.get().getProducts())) {
                shoppingListRepository.save(list.get());
                return "Products of list with id " + id + " successfully deleted!";
            } else return null;
        } else return null;
    }

    public String addProductToList(Long id, Long productId) {
        Optional<ShoppingList> list = shoppingListRepository.findById(id);

        if(list.isPresent()) {
            Optional<Product> product = productRepository.findById(productId);

            if(product.isPresent()) {
                if(list.get().getProducts() == null) {
                    Set<Product> products = new HashSet<>();
                    products.add(product.get());
                    list.get().setProducts(products);
                } else list.get().getProducts().add(product.get());

                shoppingListRepository.save(list.get());
            } else {
                return "Product";
            }
        } else {
            return "ShoppingList";
        }

        return "Product with id " + productId + " added to shopping list!";
    }
}
