package com.ShoppingCart.ShoppingCart;

import com.ShoppingCart.ShoppingCart.model.CartItem;
import com.ShoppingCart.ShoppingCart.model.Product;
import com.ShoppingCart.ShoppingCart.model.User;
import com.ShoppingCart.ShoppingCart.repository.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ShoppingCartTests {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testAddOneCartItem(){
        Product product = testEntityManager.find(Product.class,2L);
        User user = testEntityManager.find(User.class, 1L);

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setQuantity(1);

        CartItem savedCartItem = cartItemRepository.save(cartItem);

        assertTrue(savedCartItem.getId() > 0);
    }

    @Test
    public void testGetCartItemsByUser(){
        User user = new User();
        user.setId(1L);
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        assertEquals(3, cartItems.size());
    }
}
