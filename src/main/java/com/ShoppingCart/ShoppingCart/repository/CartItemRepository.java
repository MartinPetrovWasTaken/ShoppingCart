package com.ShoppingCart.ShoppingCart.repository;

import com.ShoppingCart.ShoppingCart.model.CartItem;
import com.ShoppingCart.ShoppingCart.model.Product;
import com.ShoppingCart.ShoppingCart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    public List<CartItem> findByUser(User user);
    public CartItem findByUserAndProduct(User user, Product product);
    @Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.product.id = ?2 " +
            "AND c.user.id = ?3")
    @Modifying
    public void updateQuantity(Integer quantity, Long productId, Long userId);

    @Query("DELETE FROM CartItem c WHERE c.user.id = ?1 " +
            "AND c.product.id = ?2")
    @Modifying
    public void deleteByCustomerAndProduct(Long userId, Long productId);

    @Query("DELETE FROM CartItem c WHERE c.user.id = ?1 ")
    @Modifying
    public void deleteByCustomer(Long userId);
}
