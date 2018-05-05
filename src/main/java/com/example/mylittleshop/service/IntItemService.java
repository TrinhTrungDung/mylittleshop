package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Item;

import java.util.List;

public interface IntItemService {
    List<Item> getAllItems();
    Item getItemByBarcode(String barcode);
    boolean addItem(Item item);
    void updateItem(Item item);
    void deleteItem(String barcode);
}