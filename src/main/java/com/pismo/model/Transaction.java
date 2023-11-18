package com.pismo.model;

import com.pismo.enums.OperationType;

import java.util.Date;

public record Transaction(long transactionId,
                      long accountId,
                      OperationType operationTypeId,
                      double amount,
                      Date eventDate) {}