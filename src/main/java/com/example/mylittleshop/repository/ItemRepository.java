package com.example.mylittleshop.repository;

import com.example.mylittleshop.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findByBarcode(String barcode);
}