package com.pismo.service.impl;

import com.pismo.dto.TransactionRequest;
import com.pismo.entity.Account;
import com.pismo.entity.Transaction;
import com.pismo.enums.OperationTypeEnum;
import com.pismo.exception.TransactionNotCreatedException;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.TransactionRepository;
import com.pismo.service.TransactionRequestConverter;
import com.pismo.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionRequestConverter transactionRequestConverter;

    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository,
            TransactionRequestConverter transactionRequestConverter){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionRequestConverter = transactionRequestConverter;
    }

    @Override
    public Transaction createTransaction(TransactionRequest transactionRequest) throws TransactionNotCreatedException {
        validateTransactionRequest(transactionRequest);
        Transaction transaction = transactionRequestConverter.convertTo(transactionRequest);
        transaction.setEventDate(new Date());
        return transactionRepository.save(transaction);
    }

    private void validateTransactionRequest(TransactionRequest transactionRequest) throws TransactionNotCreatedException {
        if (!hasExistingAccount(transactionRequest.accountId())) {
            throw new TransactionNotCreatedException(
                    "Account not found for the given id: " + transactionRequest.accountId());
        }
        if (!hasExistingOperationType(transactionRequest.operationTypeId())) {
            throw new TransactionNotCreatedException(
                    "Operation Type not found for the given id: " + transactionRequest.operationTypeId());
        }
    }
    
    private Boolean hasExistingAccount (Integer accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.isPresent();
    }

    private Boolean hasExistingOperationType (Integer operationTypeId) {
        return OperationTypeEnum.fromValue(operationTypeId) != null;
    }
}
