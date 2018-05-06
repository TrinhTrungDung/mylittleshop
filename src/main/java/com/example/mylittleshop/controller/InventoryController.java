package com.example.mylittleshop.controller;


import com.example.mylittleshop.entity.Inventory;
import com.example.mylittleshop.json.InventoryInfo;
import com.example.mylittleshop.service.IntInventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private IntInventoryService inventoryService;

    @GetMapping(value="inventories", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<InventoryInfo>> getAllInventories(){
        List<InventoryInfo> responseInventoryList = new ArrayList<>();
        List<Inventory> inventoryList = inventoryService.getAllInventories();

        for (Inventory inventory : inventoryList) {
            InventoryInfo inventoryInfo = new InventoryInfo();
            BeanUtils.copyProperties(inventory, inventoryInfo);
            responseInventoryList.add(inventoryInfo);
        }

        return new ResponseEntity<>(responseInventoryList, HttpStatus.OK);
    }

    @GetMapping(value="/inventories/{shopId}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<InventoryInfo>> getInventoriesByShopId(@PathVariable("shopId") Long shopId) {
        List<InventoryInfo> responseInventoryList = new ArrayList<>();
        List<Inventory> inventoryList = inventoryService.getInventoriesByShopId(shopId);

        for (Inventory inventory : inventoryList) {
            InventoryInfo inventoryInfo = new InventoryInfo();
            BeanUtils.copyProperties(inventory, inventoryInfo);
            responseInventoryList.add(inventoryInfo);
        }

        return new ResponseEntity<>(responseInventoryList, HttpStatus.OK);
    }

    @GetMapping(value="/inventories/{barcode}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<InventoryInfo>> getInventoriesByBarcode(@PathVariable("barcode") String barcode) {
        List<InventoryInfo> responseInventoryList = new ArrayList<>();
        List<Inventory> inventoryList = inventoryService.getInventoriesByBarcode(barcode);

        for (Inventory inventory : inventoryList) {
            InventoryInfo inventoryInfo = new InventoryInfo();
            BeanUtils.copyProperties(inventory, inventoryInfo);
            responseInventoryList.add(inventoryInfo);
        }

        return new ResponseEntity<>(responseInventoryList, HttpStatus.OK);
    }

    @GetMapping(value="inventory/{shopId}+{barcode}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InventoryInfo> getInventoryByShopIdAndBarcode(@PathVariable("shopId") Long shopId,
                                                                        @PathVariable("barcode") String barcode) {
        InventoryInfo inventoryInfo = new InventoryInfo();
        BeanUtils.copyProperties(inventoryService.getInventoryByShopIdAndBarcode(shopId, barcode), inventoryInfo);

        return new ResponseEntity<>(inventoryInfo, HttpStatus.OK);
    }

    @PostMapping(value="inventory", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addInventory(@RequestBody InventoryInfo inventoryInfo, UriComponentsBuilder builder) {
        Inventory inventory = new Inventory();
        BeanUtils.copyProperties(inventoryInfo, inventory);
        boolean flag = inventoryService.addInventory(inventory);

        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/inventory/{id}+{barcode}")
                .buildAndExpand(inventory.getShopId(), inventory.getBarcode()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="inventory", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InventoryInfo> updateInventory(@RequestBody InventoryInfo inventoryInfo) {
        Inventory inventory = new Inventory();
        BeanUtils.copyProperties(inventoryInfo, inventory);
        inventoryService.updateInventory(inventory);

        InventoryInfo newInventoryInfo = new InventoryInfo();
        BeanUtils.copyProperties(inventory, newInventoryInfo);

        return new ResponseEntity<>(newInventoryInfo, HttpStatus.OK);
    }

    @DeleteMapping(value="inventory/{shopId}+{barcode}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteInventory(@PathVariable("shopId") Long shopId,
                                                @PathVariable("barcode") String barcode) {
        inventoryService.deleteInventory(shopId, barcode);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
