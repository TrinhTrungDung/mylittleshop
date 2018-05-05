package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Item;
import com.example.mylittleshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService implements IntItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);

        return items;
    }

    @Override
    public Item getItemByBarcode(String barcode) {
        return itemRepository.findByBarcode(barcode);
    }

    @Override
    public synchronized boolean addItem(Item item) {
        List<Item> itemsExisted = getAllItems();

        if (itemsExisted.contains(item)) {
            return false;
        } else {
            itemRepository.save(item);
            return true;
        }
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(String barcode) {
        itemRepository.delete(getItemByBarcode(barcode));
    }
}
