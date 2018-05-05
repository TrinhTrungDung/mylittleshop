package com.example.mylittleshop.repository;

import com.example.mylittleshop.entity.Sale;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {
    Sale findDistinctById(Long id);
    List<Sale> findDistinctByShopId(Long shopId);
    List<Sale> findDistinctByBarcode(String barcode);
    List<Sale> findDistinctByUsername(String username);
    List<Sale> findDistinctByExpDate(Date date);
}