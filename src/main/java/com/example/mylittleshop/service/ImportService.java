package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Import;
import com.example.mylittleshop.repository.ImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImportService implements IntImportService {
    
    @Autowired
    private ImportRepository importRepository;
    
    @Override
    public List<Import> getAllImports() {
        List<Import> Imports = new ArrayList<>();
        importRepository.findAll().forEach(Imports::add);

        return Imports;
    }

    @Override
    public List<Import> getImportsByShopId(Long shopId) {
        return importRepository.findDistinctByShopId(shopId);
    }

    @Override
    public List<Import> getImportsByBarcode(String barcode) {
        return importRepository.findDistinctByBarcode(barcode);
    }

    @Override
    public List<Import> getImportsByUsername(String username) {
        return importRepository.findDistinctByUsername(username);
    }

    @Override
    public List<Import> getImportsByImpDate(Date date) {
        return importRepository.findDistinctByImpDate(date);
    }

    @Override
    public Import getImportById(Long id) {
        return importRepository.findDistinctById(id);
    }

    @Override
    public synchronized boolean addImport(Import imported) {
        List<Import> importsExisted = getAllImports();

        if (importsExisted.contains(imported)) {
            return false;
        } else {
            importRepository.save(imported);
            return true;
        }
    }

    @Override
    public void updateImport(Import imported) {
        importRepository.save(imported);
    }

    @Override
    public void deleteImport(Long id) {
        importRepository.delete(getImportById(id));
    }

}