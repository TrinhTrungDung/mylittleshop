package com.example.mylittleshop.repository;

import com.example.mylittleshop.entity.Inventory;
import com.example.mylittleshop.entity.InventoryID;
import com.example.mylittleshop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InventoryRepository extends JpaRepository<Inventory,InventoryID> {

    List<Inventory> findByIdShop(Shop shop);
}
