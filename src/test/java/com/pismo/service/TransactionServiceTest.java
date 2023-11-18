package com.pismo.service;

import com.pismo.dto.TransactionRequest;
import com.pismo.entity.Account;
import com.pismo.entity.Transaction;
import com.pismo.enums.OperationTypeEnum;
import com.pismo.exception.TransactionNotCreatedException;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.TransactionRepository;
import com.pismo.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRequestConverter transactionRequestConverter;
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        transactionService = new TransactionServiceImpl(
                transactionRepository, accountRepository, transactionRequestConverter);
    }

    @Test
    void givenAValidTransactionShouldReturnTransactionSuccessfully() throws TransactionNotCreatedException {
        TransactionRequest request = new TransactionRequest(1, 1, 100.0);

        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAccount(new Account(request.accountId()));
        expectedTransaction.setOperationType(OperationTypeEnum.COMPRA_A_VISTA);
        expectedTransaction.setAmount(request.amount());
        expectedTransaction.setEventDate(new Date());

        when(transactionRequestConverter.convertTo(request)).thenReturn(expectedTransaction);
        when(accountRepository.findById(request.accountId())).thenReturn(Optional.of(new Account()));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(expectedTransaction);

        Transaction createdTransaction = transactionService.createTransaction(request);

        assertNotNull(createdTransaction);
        assertEquals(expectedTransaction, createdTransaction);
    }

    @Test
    void givenInvalidAccountOnCreateTransactionShouldThrowException() {
        TransactionRequest request = new TransactionRequest(1, 1, 100.0);

        when(accountRepository.findById(request.accountId())).thenReturn(Optional.empty());

        TransactionNotCreatedException exception = assertThrows(TransactionNotCreatedException.class, () -> {
            transactionService.createTransaction(request);
        });

        assertEquals("Account not found for the given id: " + request.accountId(), exception.getMessage());

    }

    @Test
    void givenInvalidOperationTypeOnCreateTransactionShouldThrowException() {
        TransactionRequest request = new TransactionRequest(1, 5, 100.0);

        when(accountRepository.findById(request.accountId())).thenReturn(Optional.of(new Account()));

        TransactionNotCreatedException exception = assertThrows(TransactionNotCreatedException.class, () -> {
            transactionService.createTransaction(request);
        });

        assertEquals("Operation Type not found for the given id: " + request.operationTypeId(), exception.getMessage());
    }
}