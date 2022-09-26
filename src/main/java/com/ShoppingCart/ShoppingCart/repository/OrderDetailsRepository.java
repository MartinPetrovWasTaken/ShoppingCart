package com.ShoppingCart.ShoppingCart.repository;

import com.ShoppingCart.ShoppingCart.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
