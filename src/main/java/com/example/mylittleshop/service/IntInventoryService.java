package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Inventory;
import com.example.mylittleshop.entity.InventoryID;

import java.util.List;

public interface IntInventoryService {
    List<Inventory> getAllInventories();
    List<Inventory> getInventoriesByShopId(Long shopId);
    Inventory getInventoryById(InventoryID id);
    boolean addInventory(Inventory Inventory);
    void updateInventory(Inventory Inventory);
    void deleteInventory(Long shopId);
}