package com.example.mylittleshop.repository;

import com.example.mylittleshop.entity.Sale;
import com.example.mylittleshop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale,Long> {

    List<Sale> findByShop(Shop shop);
}
