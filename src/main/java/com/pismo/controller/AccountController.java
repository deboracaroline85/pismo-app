package com.pismo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/accounts")
    public String listAccounts() {
        return "Greetings from Spring Boot!";
    }
}
