package com.ShoppingCart.ShoppingCart.service;

import com.ShoppingCart.ShoppingCart.model.CustomUserDetails;
import com.ShoppingCart.ShoppingCart.model.User;
import com.ShoppingCart.ShoppingCart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(user);
    }

    public User getCurrentlyLoggedInUser(Authentication authentication){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) return null;

        User user = null;
        Object principal = authentication.getPrincipal();

        if(principal instanceof CustomUserDetails){
            String email = ((CustomUserDetails) principal).getUsername();
            user = userRepository.findByEmail(email);
        }
        return user;
    }
}
