package com.pismo.enums;

public enum OperationType {
    COMPRA_A_VISTA(1),
    COMPRA_PARCELADA(2),
    SAQUE(3),
    DEPOSITO(4);

    private Integer value;

    OperationType(Integer value) {
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }
}
