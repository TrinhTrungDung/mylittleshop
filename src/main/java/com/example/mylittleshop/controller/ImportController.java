package com.example.mylittleshop.controller;

import com.example.mylittleshop.entity.Import;
import com.example.mylittleshop.json.ImportInfo;
import com.example.mylittleshop.service.IntImportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    @Autowired
    private IntImportService importService;

    @GetMapping(value="imports", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ImportInfo>> getAllImports() {
        List<ImportInfo> responseImportList = new ArrayList<>();
        List<Import> importList = importService.getAllImports();

        for (Import imported : importList) {
            ImportInfo importInfo = new ImportInfo();
            BeanUtils.copyProperties(imported, importInfo);
            responseImportList.add(importInfo);
        }

        return new ResponseEntity<>(responseImportList, HttpStatus.OK);
    }

    @GetMapping(value="/shop/{shopId}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ImportInfo>> getImportsByShopId(@PathVariable("shopId") Long shopId) {
        List<ImportInfo> responseImportList = new ArrayList<>();
        List<Import> importList = importService.getImportsByShopId(shopId);

        for (Import imported : importList) {
            ImportInfo importInfo = new ImportInfo();
            BeanUtils.copyProperties(imported, importInfo);
            responseImportList.add(importInfo);
        }

        return new ResponseEntity<>(responseImportList, HttpStatus.OK);
    }

    @GetMapping(value="/item/{barcode}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ImportInfo>> getImportsByBarcode(@PathVariable("barcode") String barcode) {
        List<ImportInfo> responseImportList = new ArrayList<>();
        List<Import> importList = importService.getImportsByBarcode(barcode);

        for (Import imported : importList) {
            ImportInfo importInfo = new ImportInfo();
            BeanUtils.copyProperties(imported, importInfo);
            responseImportList.add(importInfo);
        }

        return new ResponseEntity<>(responseImportList, HttpStatus.OK);
    }

    @GetMapping(value="/user/{username}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ImportInfo>> getImportsByUsername(@PathVariable("username") String username) {
        List<ImportInfo> responseImportList = new ArrayList<>();
        List<Import> importList = importService.getImportsByUsername(username);

        for (Import imported : importList) {
            ImportInfo importInfo = new ImportInfo();
            BeanUtils.copyProperties(imported, importInfo);
            responseImportList.add(importInfo);
        }

        return new ResponseEntity<>(responseImportList, HttpStatus.OK);
    }

    @GetMapping(value="/date/{date}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ImportInfo>> getImportsByImpDate(@PathVariable("date") Date date) {
        List<ImportInfo> responseImportList = new ArrayList<>();
        List<Import> importList = importService.getImportsByImpDate(date);

        for (Import imported : importList) {
            ImportInfo importInfo = new ImportInfo();
            BeanUtils.copyProperties(imported, importInfo);
            responseImportList.add(importInfo);
        }

        return new ResponseEntity<>(responseImportList, HttpStatus.OK);
    }

    @GetMapping(value="import/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ImportInfo> getImportById(@PathVariable("id") Long id) {
        ImportInfo importInfo = new ImportInfo();
        BeanUtils.copyProperties(importService.getImportById(id), importInfo);

        return new ResponseEntity<>(importInfo, HttpStatus.OK);
    }

    @PostMapping(value="import", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addImport(@RequestBody ImportInfo importInfo, UriComponentsBuilder builder) {
        Import imported = new Import();
        BeanUtils.copyProperties(importInfo, imported);
        boolean flag = importService.addImport(imported);

        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/import/{id}").buildAndExpand(imported.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="import", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ImportInfo> updateImport(@RequestBody ImportInfo importInfo) {
        Import imported = new Import();
        BeanUtils.copyProperties(importInfo, imported);
        importService.updateImport(imported);

        ImportInfo newImportInfo = new ImportInfo();
        BeanUtils.copyProperties(imported, newImportInfo);

        return new ResponseEntity<>(newImportInfo, HttpStatus.OK);
    }

    @DeleteMapping(value="import/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteImport(@PathVariable("id") Long id) {
        importService.deleteImport(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}