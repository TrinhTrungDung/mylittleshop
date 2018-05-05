package com.example.mylittleshop.controller;

import com.example.mylittleshop.entity.Sale;
import com.example.mylittleshop.entity.Shop;
import com.example.mylittleshop.json.SaleInfo;
import com.example.mylittleshop.service.IntSaleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private IntSaleService saleService;

    @GetMapping(value="sales", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<SaleInfo>> getAllSales() {
        List<SaleInfo> responseSaleList = new ArrayList<>();
        List<Sale> saleList = saleService.getAllSales();

        for (Sale sale : saleList) {
            SaleInfo saleInfo = new SaleInfo();
            BeanUtils.copyProperties(sale, saleInfo);
            responseSaleList.add(saleInfo);
        }

        return new ResponseEntity<>(responseSaleList, HttpStatus.OK);
    }

    @GetMapping(value="/shop/{shopId}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<SaleInfo>> getSalesByShopId(@PathVariable("shopId") Long shopId) {
        List<SaleInfo> responseSaleList = new ArrayList<>();
        List<Sale> saleList = saleService.getSalesByShopId(shopId);

        for (Sale sale : saleList) {
            SaleInfo saleInfo = new SaleInfo();
            BeanUtils.copyProperties(sale, saleInfo);
            responseSaleList.add(saleInfo);
        }

        return new ResponseEntity<>(responseSaleList, HttpStatus.OK);
    }

    @GetMapping(value="/item/{barcode}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<SaleInfo>> getSalesByBarcode(@PathVariable("barcode") String barcode) {
        List<SaleInfo> responseSaleList = new ArrayList<>();
        List<Sale> saleList = saleService.getSalesByBarcode(barcode);

        for (Sale sale : saleList) {
            SaleInfo saleInfo = new SaleInfo();
            BeanUtils.copyProperties(sale, saleInfo);
            responseSaleList.add(saleInfo);
        }

        return new ResponseEntity<>(responseSaleList, HttpStatus.OK);
    }

    @GetMapping(value="/user/{username}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<SaleInfo>> getSalesByUsername(@PathVariable("username") String username) {
        List<SaleInfo> responseSaleList = new ArrayList<>();
        List<Sale> saleList = saleService.getSalesByUsername(username);

        for (Sale sale : saleList) {
            SaleInfo saleInfo = new SaleInfo();
            BeanUtils.copyProperties(sale, saleInfo);
            responseSaleList.add(saleInfo);
        }

        return new ResponseEntity<>(responseSaleList, HttpStatus.OK);
    }

    @GetMapping(value="/date/{date}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<SaleInfo>> getSalesByDate(@PathVariable("date") Date date) {
        List<SaleInfo> responseSaleList = new ArrayList<>();
        List<Sale> saleList = saleService.getSalesByDate(date);

        for (Sale sale : saleList) {
            SaleInfo saleInfo = new SaleInfo();
            BeanUtils.copyProperties(sale, saleInfo);
            responseSaleList.add(saleInfo);
        }

        return new ResponseEntity<>(responseSaleList, HttpStatus.OK);
    }

    @GetMapping(value="sale/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SaleInfo> getSaleById(@PathVariable("id") Long id) {
        SaleInfo saleInfo = new SaleInfo();
        BeanUtils.copyProperties(saleService.getSaleById(id), saleInfo);

        return new ResponseEntity<>(saleInfo, HttpStatus.OK);
    }

    @PostMapping(value="sale", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addSale(@RequestBody SaleInfo saleInfo, UriComponentsBuilder builder) {
        Sale sale = new Sale();
        BeanUtils.copyProperties(saleInfo, sale);
        boolean flag = saleService.addSale(sale);

        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/sale/{id}").buildAndExpand(sale.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="sale", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SaleInfo> updateSale(@RequestBody SaleInfo saleInfo) {
        Sale sale = new Sale();
        BeanUtils.copyProperties(saleInfo, sale);
        saleService.updateSale(sale);

        SaleInfo newSaleInfo = new SaleInfo();
        BeanUtils.copyProperties(sale, newSaleInfo);

        return new ResponseEntity<>(newSaleInfo, HttpStatus.OK);
    }

    @DeleteMapping(value="sale/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteSale(@PathVariable("id") Long id) {
        saleService.deleteSale(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}