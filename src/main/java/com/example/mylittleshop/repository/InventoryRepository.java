package com.example.mylittleshop.repository;

import com.example.mylittleshop.entity.Inventory;
import com.example.mylittleshop.entity.InventoryID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InventoryRepository extends CrudRepository<Inventory,InventoryID> {
    List<Inventory> findDistinctById(InventoryID shopId);
}