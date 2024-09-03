package com.example.repository;

import com.example.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuRepository  extends JpaRepository<Menu, Long>{

    Menu findByRestaurantId(Long restaurantId);
}