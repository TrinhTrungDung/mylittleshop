package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Import;

import java.util.Date;
import java.util.List;

public interface IntImportService {
    List<Import> getAllImports();
    List<Import> getImportsByShopId(Long shopId);
    List<Import> getImportsByBarcode(String barcode);
    List<Import> getImportsByUsername(String username);
    List<Import> getImportsByImpDate(Date date);
    Import getImportById(Long id);
    boolean addImport(Import Import);
    void updateImport(Import Import);
    void deleteImport(Long id);
}