package com.example.mylittleshop.repository;

import com.example.mylittleshop.entity.Import;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface ImportRepository extends CrudRepository<Import,Long> {
    Import findDistinctById(Long id);
    List<Import> findDistinctByShopId(Long shopId);
    List<Import> findDistinctByBarcode(String barcode);
    List<Import> findDistinctByUsername(String username);
    List<Import> findDistinctByImpDate(Date date);
}