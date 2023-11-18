package com.pismo.exception;

public class AccountNotCreatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccountNotCreatedException(String msg) {
        super(msg);
    }
}
