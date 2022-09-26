package com.ShoppingCart.ShoppingCart.controller;

import com.ShoppingCart.ShoppingCart.model.CartItem;
import com.ShoppingCart.ShoppingCart.model.Order;
import com.ShoppingCart.ShoppingCart.model.OrderDetails;
import com.ShoppingCart.ShoppingCart.model.User;
import com.ShoppingCart.ShoppingCart.service.CustomUserDetailsService;
import com.ShoppingCart.ShoppingCart.service.OrderDetailsService;
import com.ShoppingCart.ShoppingCart.service.OrderService;
import com.ShoppingCart.ShoppingCart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Access;
import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/order")
    public String showOrder(Model model){
        model.addAttribute("order", new Order());
        return "order";
    }

    @PostMapping("process_order")
    public String finishOrder(@ModelAttribute("order") Order order, @AuthenticationPrincipal Authentication authentication){
        User user = customUserDetailsService.getCurrentlyLoggedInUser(authentication);
        if(user == null){
            return "You must login to order";
        }

        order.setCartItemList(shoppingCartService.cartItemList(user));
        order.setUser(user);
        order.setLocalDate(LocalDate.now());
        order.setTotalPrice(orderService.getTotalPrice(user));
        if(order.getTotalPrice() != 0){
            orderService.save(order);
        }

        List<CartItem> cartItemList = shoppingCartService.cartItemList(user);

        for (CartItem cartItem : cartItemList) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderId(order.getId());
            orderDetails.setProductId(cartItem.getProduct().getId());
            orderDetails.setQuantity(cartItem.getQuantity());
            orderDetailsService.save(orderDetails);
        }
        shoppingCartService.removeProductByUser(user);
        return "redirect:/products";
    }
}
