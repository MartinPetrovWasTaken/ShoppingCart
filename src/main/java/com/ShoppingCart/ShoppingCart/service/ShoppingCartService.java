package com.ShoppingCart.ShoppingCart.service;

import com.ShoppingCart.ShoppingCart.model.CartItem;
import com.ShoppingCart.ShoppingCart.model.Product;
import com.ShoppingCart.ShoppingCart.model.User;
import com.ShoppingCart.ShoppingCart.repository.CartItemRepository;
import com.ShoppingCart.ShoppingCart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> cartItemList(User user){
        return cartItemRepository.findByUser(user);
    }

    public Integer addProduct(Long productId, Integer quantity, User user){
        Integer addedQuantity = quantity;

        Product product = productRepository.findById(productId).get();

        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);

        if(cartItem != null){
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        }else{
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setUser(user);
        }

        cartItemRepository.save(cartItem);

        return addedQuantity;
    }

    public double updateQuantity(Long productId, Integer quantity, User user){
        cartItemRepository.updateQuantity(quantity, productId, user.getId());
        Product product = productRepository.findById(productId).get();

        double subtotal = product.getPrice() * quantity;
        return subtotal;
    }

    public void removeProduct(Long productId, User user){
        cartItemRepository.deleteByCustomerAndProduct(productId, user.getId());
    }

    public void removeProductByUser(User user){
        cartItemRepository.deleteByCustomer(user.getId());
    }
}
