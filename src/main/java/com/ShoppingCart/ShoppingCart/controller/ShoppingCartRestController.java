package com.ShoppingCart.ShoppingCart.controller;

import com.ShoppingCart.ShoppingCart.model.User;
import com.ShoppingCart.ShoppingCart.service.CustomUserDetailsService;
import com.ShoppingCart.ShoppingCart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownServiceException;

@RestController
public class ShoppingCartRestController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/cart/add/{pid}/{qty}")
    public String addProductToCart(@PathVariable("pid") Long productId,
                                   @PathVariable("qty") Integer quantity,
                                   @AuthenticationPrincipal Authentication authentication){
        User user = customUserDetailsService.getCurrentlyLoggedInUser(authentication);
        if(user == null){
            return "You must login to add this product to shopping cart";
        }

        Integer addedQuantity = shoppingCartService.addProduct(productId, quantity, user);

        return addedQuantity + "item(s) of this product were added to your shopping cart.";
    }

    @PostMapping("/cart/update/{pid}/{qty}")
    public String updateQuantity(@PathVariable("pid") Long productId,
                                   @PathVariable("qty") Integer quantity,
                                   @AuthenticationPrincipal Authentication authentication){
        User user = customUserDetailsService.getCurrentlyLoggedInUser(authentication);
        if(user == null){
            return "You must login to update this product to shopping cart";
        }

        double updatedQuantity = shoppingCartService.updateQuantity(productId, quantity, user);

        return String.valueOf(updatedQuantity);
    }

    @PostMapping("/cart/remove/{pid}")
    public String removeProductFromCart(@PathVariable("pid") Long productId,
                                 @AuthenticationPrincipal Authentication authentication){
        User user = customUserDetailsService.getCurrentlyLoggedInUser(authentication);
        if(user == null){
            return "You must login to remove this product from shopping cart";
        }

        shoppingCartService.removeProduct(productId, user);

        return "The product has been removed.";
    }

}
