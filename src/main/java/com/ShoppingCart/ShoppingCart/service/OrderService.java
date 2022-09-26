package com.ShoppingCart.ShoppingCart.service;

import com.ShoppingCart.ShoppingCart.model.CartItem;
import com.ShoppingCart.ShoppingCart.model.Order;
import com.ShoppingCart.ShoppingCart.model.User;
import com.ShoppingCart.ShoppingCart.repository.CartItemRepository;
import com.ShoppingCart.ShoppingCart.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public double getTotalPrice(User user){
        List<CartItem> cartItemList = cartItemRepository.findByUser(user);
        double sum = 0;
        for (CartItem cartItem : cartItemList) {
            sum += cartItem.getSubtotal();
        }
        return sum;
    }



}
