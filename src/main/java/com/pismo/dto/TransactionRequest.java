package com.pismo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionRequest (
        @JsonProperty("account_id")
        Integer accountId,
        @JsonProperty("operation_type_id")
        Integer operationTypeId,
        Double amount) {}