package com.joaopimentel.ShoppingCart.modules.service;

import com.joaopimentel.ShoppingCart.configuration.utils.ResourceNotFoundException;
import com.joaopimentel.ShoppingCart.configuration.utils.UserPrincipal;
import com.joaopimentel.ShoppingCart.modules.entity.User;
import com.joaopimentel.ShoppingCart.modules.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final
    UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email : " + email)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}