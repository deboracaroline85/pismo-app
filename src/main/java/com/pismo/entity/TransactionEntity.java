package com.pismo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    private long id;

    private long account_id;
    private Integer operation_type_id;
    private double amount;
    private Date event_date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public Integer getOperation_type_id() {
        return operation_type_id;
    }

    public void setOperation_type_id(Integer operation_type_id) {
        this.operation_type_id = operation_type_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }
}
