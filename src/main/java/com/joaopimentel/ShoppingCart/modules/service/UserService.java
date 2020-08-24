package com.joaopimentel.ShoppingCart.modules.service;

import com.joaopimentel.ShoppingCart.configuration.utils.UserPrincipal;
import com.joaopimentel.ShoppingCart.modules.dto.UserDto;
import com.joaopimentel.ShoppingCart.modules.entity.User;
import com.joaopimentel.ShoppingCart.modules.mappper.UserMapping;
import com.joaopimentel.ShoppingCart.modules.repository.ShoppingListRepository;
import com.joaopimentel.ShoppingCart.modules.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapping userMapping;

    public UserService(UserRepository userRepository, UserMapping userMapping, ShoppingListRepository shoppingListRepository) {
        this.userRepository = userRepository;
        this.userMapping = userMapping;
    }

    public UserDto getCurrentUser(UserPrincipal userPrincipal) {
        Optional<User> user = userRepository.findById(userPrincipal.getId());
        return user.map(userMapping::toDto).orElse(null);
    }

    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapping::toDto).orElse(null);
    }

    public String deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            userRepository.deleteById(id);
            return "User with id " + id + " successfully deleted!";
        } else return null;
    }

    public String deleteAllListOfUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            if(user.get().getLists().removeAll(user.get().getLists())) {
                userRepository.save(user.get());
                return "Lists of user with id " + id + " successfully deleted!";
            } else return null;
        } else return null;
    }
}
