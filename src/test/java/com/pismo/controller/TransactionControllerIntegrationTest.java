package com.pismo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.dto.TransactionRequest;
import com.pismo.entity.Account;
import com.pismo.entity.Transaction;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.TransactionRepository;
import com.pismo.service.TransactionRequestConverter;
import com.pismo.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRequestConverter transactionRequestConverter;
    @Autowired
    private TransactionService transactionService;

    @Test
    void givenValidTransactionShouldReturnCreatedStatus() throws Exception {
        Account account = new Account();
        account.setDocumentNumber("12345678900");
        accountRepository.save(account);

        TransactionRequest request = new TransactionRequest(account.getId(), 1, 100.0);

        MvcResult result = mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        String createdTransaction = result.getResponse().getContentAsString();
        assertNotNull(createdTransaction);
    }

    @Test
    void givenInvalidTransactionShouldReturnBadRequestStatus() throws Exception {
        TransactionRequest request = new TransactionRequest(5, 1, 100.0);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
