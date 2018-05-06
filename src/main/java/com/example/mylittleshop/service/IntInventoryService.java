package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Inventory;

import java.util.List;

public interface IntInventoryService {
    List<Inventory> getAllInventories();
    List<Inventory> getInventoriesByShopId(Long shopId);
    List<Inventory> getInventoriesByBarcode(String barcode);
    Inventory getInventoryByShopIdAndBarcode(Long shopId, String barcode);
    boolean addInventory(Inventory Inventory);
    void updateInventory(Inventory Inventory);
    void deleteInventory(Long shopId, String barcode);
}