package com.apelie.apelieapi.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StoreCategory {
    BEBIDAS,
    CERVEJAS,
    ARGILA,
    COMIDAS,
    ROUPAS,
    MADEIRA,
    JARDINAGEM,
    ENFEITES,
    TRICO,
    COSTURA;

    @JsonValue
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
