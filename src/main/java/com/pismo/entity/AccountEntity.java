package com.pismo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    private Long id;
    private String document_number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }
}
