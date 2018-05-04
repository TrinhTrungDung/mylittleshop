package com.example.mylittleshop.controller;


import com.example.mylittleshop.entity.Inventory;
import com.example.mylittleshop.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    //Get all inventories
    @GetMapping("/all")
    public Iterable<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }

    @PostMapping
    ResponseEntity<Inventory> insertInventory(@RequestBody Inventory inventory) {
        inventoryRepository.save(inventory);

        return ResponseEntity.accepted().build();
    }

}
