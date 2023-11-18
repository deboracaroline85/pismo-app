package com.pismo.service;

import com.pismo.entity.Account;
import com.pismo.exception.AccountNotCreatedException;
import com.pismo.exception.AccountNotFoundException;
import com.pismo.repository.AccountRepository;
import com.pismo.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl(accountRepository);
    }

    private Account buildAccount() {
        Account account = new Account();
        account.setId(1);
        account.setDocumentNumber("12345678900");
        return account;
    }

    @Test
    void givenAnExistingAccountShouldFetchDataCorrectly() {
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(buildAccount()));
        Account acc = accountService.getAccount(1);
        assertEquals(acc.getDocumentNumber(), "12345678900");
    }

    @Test
    void givenCorrectDocumentNumberFormatInAccountCreationShouldCreateItSuccessfully() {
        Account account = new Account();
        account.setId(1);
        account.setDocumentNumber("10020030045");
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account accountToCreate = new Account();
        accountToCreate.setDocumentNumber("10020030045");
        Account accountCreated = accountService.createAccount(accountToCreate);

        assertEquals(accountCreated.getId(), 1);
        assertEquals(accountCreated.getDocumentNumber(), "10020030045");
    }

    @Test
    void givenNoExistingAccountShouldThrowAnException() {
        when(accountRepository.findById(anyInt())).thenThrow(new AccountNotFoundException("Account not found for id 2"));
        AccountNotFoundException thrown = Assertions.assertThrows(AccountNotFoundException.class, () ->
                accountService.getAccount(2));
        assertEquals("Account not found for id 2", thrown.getMessage());

    }

    @Test
    void givenNoDocumentNumberInAccountCreationShouldThrowAnException() {
        when(accountRepository.save(any(Account.class))).thenThrow(new AccountNotCreatedException("Field document_number is required"));
        Account acc = new Account();
        acc.setDocumentNumber("");
        AccountNotCreatedException thrown = Assertions.assertThrows(AccountNotCreatedException.class, () ->
                accountService.createAccount(acc));
        assertEquals("Field document_number is required", thrown.getMessage());

    }
}
