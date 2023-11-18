package com.pismo.service;

import com.pismo.entity.Account;
import com.pismo.exception.AccountNotCreatedException;
import com.pismo.exception.AccountNotFoundException;

public interface AccountService {
    Account createAccount(Account account) throws AccountNotCreatedException;
    Account getAccount(Integer accountId) throws AccountNotFoundException;
}
