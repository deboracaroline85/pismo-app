package com.pismo.controller;

import com.pismo.entity.Account;
import com.pismo.exception.AccountNotCreatedException;
import com.pismo.exception.AccountNotFoundException;
import com.pismo.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    AccountServiceImpl accountService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        accountController = new AccountController(accountService);
    }

    private Account buildAccount() {
        Account account = new Account();
        account.setId(1);
        account.setDocumentNumber("12345678900");
        return account;
    }

    @Test
    void givenAnExistingAccountIdShouldSuccesfullyReturnAccount(){
        when(accountService.getAccount(anyInt())).thenReturn(buildAccount());
        Account acc = accountController.getAccount(1).getBody();
        assertEquals(acc.getDocumentNumber(), "12345678900");
    }

    @Test
    void givenACorrectDocumentNumberShouldCreateAnAccount(){
        Account accountToCreate = new Account();
        accountToCreate.setDocumentNumber("11122233344");
        when(accountService.createAccount(any(Account.class))).thenReturn(accountToCreate);

        Account acc = new Account();
        acc.setDocumentNumber("11122233344");
        Account accountCreated = accountController.createAccount(acc).getBody();
        assertEquals(accountCreated.getDocumentNumber(), "11122233344");
    }

    @Test
    void givenAnNonExistingAccountIdShouldThrowAnException(){
        when(accountService.getAccount(anyInt())).thenThrow(new AccountNotFoundException("Account not found for id 5"));
        AccountNotFoundException thrown = Assertions.assertThrows(AccountNotFoundException.class, () ->
                accountController.getAccount(5).getBody());
        assertEquals("Account not found for id 5", thrown.getMessage());
    }

    @Test
    void givenNoDocumentNumberInAccountCreationShouldThrowAnException() {
        when(accountService.createAccount(any(Account.class))).thenThrow(new AccountNotCreatedException("Field document_number is required"));
        Account acc = new Account();
        acc.setDocumentNumber("");
        AccountNotCreatedException thrown = Assertions.assertThrows(AccountNotCreatedException.class, () ->
                accountController.createAccount(acc));
        assertEquals("Field document_number is required", thrown.getMessage());

    }

}
