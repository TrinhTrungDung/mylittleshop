package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Inventory;
import com.example.mylittleshop.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService implements IntInventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAllInventories() {
        List<Inventory> inventories = new ArrayList<>();
        inventoryRepository.findAll().forEach(inventories::add);

        return inventories;
    }

    @Override
    public List<Inventory> getInventoriesByShopId(Long shopId) {
        return inventoryRepository.findDistinctByShopId(shopId);
    }

    @Override
    public List<Inventory> getInventoriesByBarcode(String barcode) {
        return inventoryRepository.findDistinctByBarcode(barcode);
    }

    @Override
    public Inventory getInventoryByShopIdAndBarcode(Long shopId, String barcode) {
        return inventoryRepository.findDistinctByShopIdAndBarcode(shopId, barcode);
    }

    @Override
    public synchronized boolean addInventory(Inventory inventory) {
        List<Inventory> inventoriesExisted = getAllInventories();

        if (inventoriesExisted.contains(inventory)) {
            return false;
        } else {
            inventoryRepository.save(inventory);
            return true;
        }
    }

    @Override
    public void updateInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(Long shopId, String barcode) {
        inventoryRepository.delete(getInventoryByShopIdAndBarcode(shopId, barcode));
    }
}
