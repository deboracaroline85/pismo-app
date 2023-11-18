package com.pismo.service.impl;

import com.pismo.entity.Account;
import com.pismo.exception.AccountNotCreatedException;
import com.pismo.exception.AccountNotFoundException;
import com.pismo.repository.AccountRepository;
import com.pismo.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) throws AccountNotCreatedException {
        if(account.getDocumentNumber() == null || account.getDocumentNumber().isEmpty()){
            throw new AccountNotCreatedException("Field document_number is required");
        }
        return accountRepository.save(account);

    }

    @Override
    public Account getAccount(Integer accountId) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for id " + accountId));
        return account;
    }

}
