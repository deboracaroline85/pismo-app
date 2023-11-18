package com.pismo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "operation_type")
public class OperationTypeEntity {

    @Id
    private Integer id;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
