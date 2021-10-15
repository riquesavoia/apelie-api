package com.apelie.apelieapi.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    CANCELED,
    AWAITING_PAYMENT,
    AWAITING_SHIPPING,
    SHIPPING,
    FINISHED;

    @JsonValue
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
