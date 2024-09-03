package com.example.repository;

import java.util.List;

import com.example.menu.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{

    List<MenuItem> findAllByMenuId(Long id);

}