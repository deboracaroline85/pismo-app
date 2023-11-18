package com.pismo.controller;

import com.pismo.dto.TransactionRequest;
import com.pismo.entity.Transaction;
import com.pismo.exception.TransactionNotCreatedException;
import com.pismo.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/transactions", produces = "application/json")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction createdTransaction = transactionService.createTransaction(transactionRequest);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }
}
