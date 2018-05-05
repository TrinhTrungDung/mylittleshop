package com.example.mylittleshop.controller;

import com.example.mylittleshop.entity.Account;
import com.example.mylittleshop.json.AccountInfo;
import com.example.mylittleshop.service.IntAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private IntAccountService accountService;

    @GetMapping(value="users", produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AccountInfo>> getAllAccounts() {
        List<AccountInfo> responseAccountList = new ArrayList<>();
        List<Account> accountList = accountService.getAllAccounts();

        for (Account account : accountList) {
            AccountInfo accountInfo = new AccountInfo();
            BeanUtils.copyProperties(account, accountInfo);
            responseAccountList.add(accountInfo);
        }

        return new ResponseEntity<>(responseAccountList, HttpStatus.OK);
    }

    @GetMapping(value="user/{username}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AccountInfo> getAccountByUsername(@PathVariable("username") String username) {
        AccountInfo accountInfo = new AccountInfo();
        BeanUtils.copyProperties(accountService.getAccountByUsername(username), accountInfo);

        return new ResponseEntity<>(accountInfo, HttpStatus.OK);
    }

    @PostMapping(value="user", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> addAccount(@RequestBody AccountInfo accountInfo, UriComponentsBuilder builder) {
        Account account = new Account();
        BeanUtils.copyProperties(accountInfo, account);
        boolean flag = accountService.addAccount(account);

        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/user/{username}").buildAndExpand(account.getUsername()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="user", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AccountInfo> updateAccount(@RequestBody AccountInfo accountInfo) {
        Account account = new Account();
        BeanUtils.copyProperties(accountInfo, account);
        accountService.updateAccount(account);

        AccountInfo newAccountInfo = new AccountInfo();
        BeanUtils.copyProperties(account, newAccountInfo);

        return new ResponseEntity<>(newAccountInfo, HttpStatus.OK);
    }

    @DeleteMapping(value="user/{username}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteAccount(@PathVariable("username") String username) {
        accountService.deleteAccount(username);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}