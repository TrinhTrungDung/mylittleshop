package com.example.mylittleshop.repository;

import com.example.mylittleshop.entity.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    List<Inventory> findDistinctByShopId(Long shopId);
    List<Inventory> findDistinctByBarcode(String barcode);
    Inventory findDistinctByShopIdAndBarcode(Long shopId, String barcode);
}