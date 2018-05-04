package com.example.mylittleshop.controller;

import com.example.mylittleshop.entity.Shop;
import com.example.mylittleshop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    // Get All shops
    @GetMapping("/all")
    public Iterable<Shop>  getAllShops() {
        return shopRepository.findAll();
    }

    @GetMapping("/{id}")
    public Shop shop(@PathVariable("id") Long id) {
        return shopRepository.findById(id).get();
    }

    @PostMapping
    ResponseEntity<Shop> insertAccount(@RequestBody Shop shop) {
        shopRepository.save(shop);

        return ResponseEntity.accepted().build();
    }

}
