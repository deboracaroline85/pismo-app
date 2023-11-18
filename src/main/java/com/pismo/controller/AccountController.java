package com.pismo.controller;

import com.pismo.entity.Account;
import com.pismo.exception.AccountNotCreatedException;
import com.pismo.exception.AccountNotFoundException;
import com.pismo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping(value ="/accounts/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") Integer id) throws AccountNotFoundException {
        Account account = accountService.getAccount(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping(value = "/accounts", produces = "application/json")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) throws AccountNotCreatedException {
        var createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
}
