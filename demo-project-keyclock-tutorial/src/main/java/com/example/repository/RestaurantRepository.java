package com.example.repository;
import com.example.menu.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
}