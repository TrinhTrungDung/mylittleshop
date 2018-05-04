package com.example.mylittleshop.controller;

import com.example.mylittleshop.entity.Account;
import com.example.mylittleshop.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(path="/create") // Map ONLY GET Requests
    @ResponseBody
    public String createUser (@RequestParam String name
            , @RequestParam String password, @RequestParam String role) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Account e = new Account();
        e.setName(name);
        e.setRole(role);
        e.setPassword(password);
        accountRepository.save(e);

        return "Saved";
    }

    @GetMapping(path = "/account")
    @ResponseBody
    public Account getAccountByUsername(@RequestParam String username) {
        return accountRepository.findByUsername(username);
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @PostMapping
    ResponseEntity<Account> insertAccount(@RequestBody Account account) {
        accountRepository.save(account);

        return ResponseEntity.accepted().build();
    }

}