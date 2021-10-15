package com.apelie.apelieapi.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethod {
    PIX,
    CREDIT_CARD,
    BOLETO;

    @JsonValue
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
