package com.pismo.exception;

public class TransactionNotCreatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TransactionNotCreatedException(String msg) {
        super(msg);
    }
}
