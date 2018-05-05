package com.example.mylittleshop.controller;


import com.example.mylittleshop.entity.Item;
import com.example.mylittleshop.json.ItemInfo;
import com.example.mylittleshop.service.IntItemService;
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
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private IntItemService itemService;

    @GetMapping(value="items", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ItemInfo>> getAllItems() {
        List<ItemInfo> responseItemList = new ArrayList<>();
        List<Item> itemList = itemService.getAllItems();

        for (Item item : itemList) {
            ItemInfo itemInfo = new ItemInfo();
            BeanUtils.copyProperties(item, itemInfo);
            responseItemList.add(itemInfo);
        }
        return new ResponseEntity<>(responseItemList, HttpStatus.OK);
    }

    @GetMapping(value="item/{barcode}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemInfo> getItemByBarcode(@PathVariable("barcode") String barcode) {
        ItemInfo itemInfo = new ItemInfo();
        BeanUtils.copyProperties(itemService.getItemByBarcode(barcode), itemInfo);

        return new ResponseEntity<>(itemInfo, HttpStatus.OK);
    }

    @PostMapping(value="item", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addItem(@RequestBody ItemInfo itemInfo, UriComponentsBuilder builder) {
        Item item = new Item();
        BeanUtils.copyProperties(itemInfo, item);
        boolean flag = itemService.addItem(item);

        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/item/{barcode}").buildAndExpand(item.getCode()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="item", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemInfo> updateItem(@RequestBody ItemInfo itemInfo) {
        Item item = new Item();
        BeanUtils.copyProperties(itemInfo, item);
        itemService.updateItem(item);

        ItemInfo newItemInfo = new ItemInfo();
        BeanUtils.copyProperties(item, newItemInfo);

        return new ResponseEntity<>(newItemInfo, HttpStatus.OK);
    }

    @DeleteMapping(value="item/{barcode}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteItem(@PathVariable("barcode") String barcode) {
        itemService.deleteItem(barcode);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}