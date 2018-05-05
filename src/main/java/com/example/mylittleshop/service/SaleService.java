package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Sale;
import com.example.mylittleshop.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SaleService implements IntSaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        saleRepository.findAll().forEach(sales::add);

        return sales;
    }

    @Override
    public List<Sale> getSalesByShopId(Long shopId) {
        return saleRepository.findDistinctByShopId(shopId);
    }

    @Override
    public List<Sale> getSalesByBarcode(String barcode) {
        return saleRepository.findDistinctByBarcode(barcode);
    }

    @Override
    public List<Sale> getSalesByUsername(String username) {
        return saleRepository.findDistinctByUsername(username);
    }

    @Override
    public List<Sale> getSalesByDate(Date date) {
        return saleRepository.findDistinctByExpDate(date);
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleRepository.findDistinctById(id);
    }

    @Override
    public synchronized boolean addSale(Sale sale) {
        List<Sale> salesExisted = getAllSales();

        if (salesExisted.contains(sale)) {
            return false;
        } else {
            saleRepository.save(sale);
            return true;
        }
    }

    @Override
    public void updateSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public void deleteSale(Long id) {
        saleRepository.delete(getSaleById(id));
    }

}