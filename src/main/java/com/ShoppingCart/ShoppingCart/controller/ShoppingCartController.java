package com.ShoppingCart.ShoppingCart.controller;

import com.ShoppingCart.ShoppingCart.model.CartItem;
import com.ShoppingCart.ShoppingCart.model.User;
import com.ShoppingCart.ShoppingCart.service.CustomUserDetailsService;
import com.ShoppingCart.ShoppingCart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model, @AuthenticationPrincipal Authentication authentication){
        User user = customUserDetailsService.getCurrentlyLoggedInUser(authentication);
        if(user == null){
            String error = "null";
            model.addAttribute("errorMsg", error);
        }
        List<CartItem> cartItems = shoppingCartService.cartItemList(user);
        model.addAttribute("cartItems", cartItems);

        return "shopping_cart";
    }
}
