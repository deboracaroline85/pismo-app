package com.pismo.enums;

public enum OperationTypeEnum {
    COMPRA_A_VISTA(1),
    COMPRA_PARCELADA(2),
    SAQUE(3),
    PAGAMENTO(4);

    private Integer value;

    OperationTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }

    public static OperationTypeEnum fromValue(int value) {
        for (OperationTypeEnum operationTypeEnum : values()) {
            if (operationTypeEnum.value == value) {
                return operationTypeEnum;
            }
        }
        return null;
    }
}
