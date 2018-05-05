package com.example.mylittleshop.service;

import com.example.mylittleshop.entity.Account;
import com.example.mylittleshop.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IntAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);

        return accounts;
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public synchronized boolean addAccount(Account account) {
        List<Account> accountsExisted = getAllAccounts();

        if (accountsExisted.contains(account)) {
            return false;
        } else {
            accountRepository.save(account);
            return true;
        }
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(String username) {
        accountRepository.delete(getAccountByUsername(username));
    }

}