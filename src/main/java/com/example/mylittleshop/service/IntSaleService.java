package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Sale;

import java.util.Date;
import java.util.List;

public interface IntSaleService {
    List<Sale> getAllSales();
    List<Sale> getSalesByShopId(Long shopId);
    List<Sale> getSalesByBarcode(String barcode);
    List<Sale> getSalesByUsername(String username);
    List<Sale> getSalesByDate(Date date);
    Sale getSaleById(Long id);
    boolean addSale(Sale sale);
    void updateSale(Sale sale);
    void deleteSale(Long id);
}
