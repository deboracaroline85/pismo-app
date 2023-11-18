package com.pismo.service;

import com.pismo.dto.TransactionRequest;
import com.pismo.entity.Account;
import com.pismo.entity.Transaction;
import com.pismo.enums.OperationTypeEnum;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TransactionRequestConverter {

    public Transaction convertTo(TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setAccount(new Account(request.accountId()));
        transaction.setOperationType(OperationTypeEnum.fromValue(request.operationTypeId()));
        transaction.setAmount(request.amount());
        transaction.setEventDate(new Date());
        return transaction;
    }
}