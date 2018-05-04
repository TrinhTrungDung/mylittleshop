package com.example.mylittleshop.controller;

import com.example.mylittleshop.entity.Import;
import com.example.mylittleshop.entity.Sale;
import com.example.mylittleshop.entity.Shop;
import com.example.mylittleshop.repository.ImportRepository;
import com.example.mylittleshop.repository.SaleRepository;
import com.example.mylittleshop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    @Autowired
    private ImportRepository importRepository;

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/all")
    public Iterable<Import> getAllSales(){
        return importRepository.findAll();
    }

    @GetMapping(value = "shop/{id}")
    public List<Import> shop(@PathVariable("id") Long id, Model model) {
        Shop shop = shopRepository.findById(id).get();
        return importRepository.findByShop(shop);
    }

    @PostMapping
    ResponseEntity<Import> insertImport(@RequestBody Import imported) {
        importRepository.save(imported);

        return ResponseEntity.accepted().build();
    }
}
