package com.joaopimentel.ShoppingCart.modules.controller;

import com.joaopimentel.ShoppingCart.configuration.utils.ApiResponse;
import com.joaopimentel.ShoppingCart.configuration.utils.CurrentUser;
import com.joaopimentel.ShoppingCart.configuration.utils.ResourceNotFoundException;
import com.joaopimentel.ShoppingCart.configuration.utils.UserPrincipal;
import com.joaopimentel.ShoppingCart.modules.dto.UserDto;
import com.joaopimentel.ShoppingCart.modules.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserDto getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        UserDto user = userService.getCurrentUser(userPrincipal);

        if(user != null) return user;
        else throw new ResourceNotFoundException("User", "id", userPrincipal.getId());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);

        if(user != null) return user;
        else throw new ResourceNotFoundException("User", "id", id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        String userDeleted = userService.deleteUserById(id);

        if(userDeleted != null) return ResponseEntity.ok(new ApiResponse(true, userDeleted));
        else throw new ResourceNotFoundException("User", "id", id);
    }

    @DeleteMapping("/{id}/list/all")
    public ResponseEntity<?> deleteAllListsOfUser(@PathVariable Long id) {
        String userDeleted = userService.deleteAllListOfUser(id);

        if(userDeleted != null) return ResponseEntity.ok(new ApiResponse(true, userDeleted));
        else throw new ResourceNotFoundException("User", "id", id);
    }
}
