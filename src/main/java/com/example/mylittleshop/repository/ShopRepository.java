package com.example.mylittleshop.repository;

import com.example.mylittleshop.entity.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {
    Shop findDistinctById(Long shopId);
}