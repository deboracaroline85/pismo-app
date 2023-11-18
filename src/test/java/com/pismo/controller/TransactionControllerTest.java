package com.pismo.controller;

import com.pismo.dto.TransactionRequest;
import com.pismo.entity.Account;
import com.pismo.entity.Transaction;
import com.pismo.enums.OperationTypeEnum;
import com.pismo.exception.AccountNotCreatedException;
import com.pismo.exception.AccountNotFoundException;
import com.pismo.exception.TransactionNotCreatedException;
import com.pismo.service.impl.AccountServiceImpl;
import com.pismo.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    TransactionServiceImpl transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        transactionController = new TransactionController(transactionService);
    }

    private Transaction buildTransaction() {
        Transaction transaction = new Transaction();
        Account account = new Account();
        account.setId(1);
        account.setDocumentNumber("12345678900");
        transaction.setAccount(account);
        transaction.setOperationType(OperationTypeEnum.PAGAMENTO);
        transaction.setEventDate(new Date());
        transaction.setAmount(100.0);
        return transaction;
    }

    @Test
    void givenACorrectTransactionRequestShouldCreateATransaction(){
        Transaction transactionToCreate = new Transaction();
        Account account = new Account();
        account.setId(1);
        account.setDocumentNumber("12345678900");
        transactionToCreate.setAccount(account);
        transactionToCreate.setOperationType(OperationTypeEnum.PAGAMENTO);
        transactionToCreate.setEventDate(new Date());
        transactionToCreate.setAmount(100.0);
        when(transactionService.createTransaction(any(TransactionRequest.class))).thenReturn(transactionToCreate);

        TransactionRequest transactionRequest = new TransactionRequest(1,4, 100.0);
        Transaction transactionCreated = transactionController.createTransaction(transactionRequest).getBody();
        assertNotNull(transactionCreated);
    }

    @Test
    void givenAnNonExistingAccountIdShouldThrowAnException(){
        when(transactionService.createTransaction(any(TransactionRequest.class))).thenThrow(new TransactionNotCreatedException("Account not found for id: 5"));
        TransactionRequest transactionRequest = new TransactionRequest(5,4, 100.0);
        TransactionNotCreatedException thrown = Assertions.assertThrows(TransactionNotCreatedException.class, () ->
                transactionController.createTransaction(transactionRequest).getBody());
        assertEquals("Account not found for id: 5", thrown.getMessage());
    }

    @Test
    void givenAnNonExistingOperationTypeIdShouldThrowAnException(){
        when(transactionService.createTransaction(any(TransactionRequest.class))).thenThrow(new TransactionNotCreatedException("Operation Type not found for id: 5"));
        Account account = new Account();
        account.setId(1);
        account.setDocumentNumber("12345678900");
        TransactionRequest transactionRequest = new TransactionRequest(1,5, 100.0);
        TransactionNotCreatedException thrown = Assertions.assertThrows(TransactionNotCreatedException.class, () ->
                transactionController.createTransaction(transactionRequest).getBody());
        assertEquals("Operation Type not found for id: 5", thrown.getMessage());
    }

}
