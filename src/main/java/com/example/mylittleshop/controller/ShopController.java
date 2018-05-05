package com.example.mylittleshop.controller;

import com.example.mylittleshop.entity.Shop;
import com.example.mylittleshop.json.ShopInfo;
import com.example.mylittleshop.service.IntShopService;
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
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private IntShopService shopService;

    @GetMapping(value="shops", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ShopInfo>>  getAllShops() {
        List<ShopInfo> responseShopList = new ArrayList<>();
        List<Shop> shopList = shopService.getAllShops();

        for (Shop shop : shopList) {
            ShopInfo shopInfo = new ShopInfo();
            BeanUtils.copyProperties(shop, shopInfo);
            responseShopList.add(shopInfo);
        }

        return new ResponseEntity<>(responseShopList, HttpStatus.OK);
    }

    @GetMapping(value="shop/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ShopInfo> getShopById(@PathVariable("id") Long id) {
        ShopInfo shopInfo = new ShopInfo();
        BeanUtils.copyProperties(shopService.getShopById(id), shopInfo);

        return new ResponseEntity<>(shopInfo, HttpStatus.OK);
    }

    @PostMapping(value="shop", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addShop(@RequestBody ShopInfo shopInfo, UriComponentsBuilder builder) {
        Shop shop = new Shop();
        BeanUtils.copyProperties(shopInfo, shop);
        boolean flag = shopService.addShop(shop);

        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/shop/{id}").buildAndExpand(shop.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="shop", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ShopInfo> updateShop(@RequestBody ShopInfo shopInfo) {
        Shop shop = new Shop();
        BeanUtils.copyProperties(shopInfo, shop);
        shopService.updateShop(shop);

        ShopInfo newShopInfo = new ShopInfo();
        BeanUtils.copyProperties(shop, newShopInfo);

        return new ResponseEntity<>(newShopInfo, HttpStatus.OK);
    }

    @DeleteMapping(value="shop/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteShop(@PathVariable("id") Long id) {
        shopService.deleteShop(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}