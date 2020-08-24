package com.joaopimentel.ShoppingCart.modules.controller;

import com.joaopimentel.ShoppingCart.configuration.utils.ApiResponse;
import com.joaopimentel.ShoppingCart.configuration.utils.ResourceNotFoundException;
import com.joaopimentel.ShoppingCart.modules.dto.ListRequest;
import com.joaopimentel.ShoppingCart.modules.dto.ShoppingListDto;
import com.joaopimentel.ShoppingCart.modules.service.ShoppingListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/list")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/{id}")
    public ShoppingListDto getShoppingListById(@PathVariable Long id) {
        ShoppingListDto list = shoppingListService.getShoppingListById(id);

        if(list != null) return list;
        else throw new ResourceNotFoundException("List", "id", id);
    }

    @PostMapping()
    public ShoppingListDto createShoppingList(@RequestBody @Valid ListRequest listRequest) {
        ShoppingListDto list = shoppingListService.createShoppingList(listRequest);

        if(list != null) return list;
        else throw new ResourceNotFoundException("User", "id", listRequest.getUserId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShoppingListById(@PathVariable Long id) {
        String listDeleted = shoppingListService.deleteShoppingListById(id);

        if(listDeleted != null) return ResponseEntity.ok(new ApiResponse(true , listDeleted));
        else throw new ResourceNotFoundException("ShoppingList", "id", id);
    }

    @DeleteMapping("/{id}/product/all")
    public ResponseEntity<?> deleteAllProductsOfList(@PathVariable Long id) {
        String listDeleted = shoppingListService.deleteAllProductsOfList(id);

        if(listDeleted != null) return ResponseEntity.ok(new ApiResponse(true, listDeleted));
        else throw new ResourceNotFoundException("ShoppingList", "id", id);
    }

    @PutMapping("/{id}/product/{productId}")
    public ResponseEntity<?> addProductToList(@PathVariable Long id, @PathVariable Long productId) {
        String addProductList = shoppingListService.addProductToList(id, productId);

        if(addProductList.contains("added")) return ResponseEntity.ok(new ApiResponse(true, addProductList));
        else if(addProductList.contains("ShoppingList")) throw new ResourceNotFoundException(addProductList, "id", id);
        else throw new ResourceNotFoundException(addProductList, "id", productId);
    }
}
