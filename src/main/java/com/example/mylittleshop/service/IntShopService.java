package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Shop;

import java.util.List;

public interface IntShopService {
    List<Shop> getAllShops();
    Shop getShopById(Long shopId);
    boolean addShop(Shop shop);
    void updateShop(Shop shop);
    void deleteShop(Long shopId);
}
