package com.example.mylittleshop.repository;

import com.example.mylittleshop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
