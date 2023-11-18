package com.pismo.entity;

import com.pismo.enums.OperationTypeEnum;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "operation_type_id")
    private OperationTypeEnum operationType;

    private Double amount;

    @Column(name = "event_date")
    private Date eventDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public OperationTypeEnum getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationTypeEnum operationType) {
        this.operationType = operationType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date event_date) {
        this.eventDate = event_date;
    }
}