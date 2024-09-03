package com.example.repository;
import java.util.List;

import com.example.menu.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findByRestaurantId(Long restaurantId);

}
