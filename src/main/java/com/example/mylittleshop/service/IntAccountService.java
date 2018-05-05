package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Account;

import java.util.List;

public interface IntAccountService {
    List<Account> getAllAccounts();
    Account getAccountByUsername(String username);
    boolean addAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(String username);
}
