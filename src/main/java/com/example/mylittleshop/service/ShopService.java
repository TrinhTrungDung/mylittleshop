package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Shop;
import com.example.mylittleshop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService implements IntShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public List<Shop> getAllShops() {
        List<Shop> shops = new ArrayList<>();
        shopRepository.findAll().forEach(shops::add);

        return shops;
    }

    @Override
    public Shop getShopById(Long shopId) {
        return shopRepository.findDistinctById(shopId);
    }

    @Override
    public synchronized boolean addShop(Shop shop) {
        List<Shop> shopsExisted = getAllShops();

        if (shopsExisted.contains(shop)) {
            return false;
        } else {
            shopRepository.save(shop);
            return true;
        }
    }

    @Override
    public void updateShop(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public void deleteShop(Long shopId) {
        shopRepository.delete(getShopById(shopId));
    }
}
