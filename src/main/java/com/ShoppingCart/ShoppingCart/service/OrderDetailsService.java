package com.ShoppingCart.ShoppingCart.service;

import com.ShoppingCart.ShoppingCart.model.Order;
import com.ShoppingCart.ShoppingCart.model.OrderDetails;
import com.ShoppingCart.ShoppingCart.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetails save(OrderDetails orderDetails){
        return orderDetailsRepository.save(orderDetails);
    }

    
}
