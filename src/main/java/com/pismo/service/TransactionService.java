package com.pismo.service;

import com.pismo.dto.TransactionRequest;
import com.pismo.entity.Transaction;
import com.pismo.exception.TransactionNotCreatedException;

public interface TransactionService {
    Transaction createTransaction (TransactionRequest transactionRequest) throws TransactionNotCreatedException;
}
