package com.example.repository;

import java.util.List;

import com.example.menu.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

    List<OrderItem> findByOrderId(Long id);

}