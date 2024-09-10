package com.erc.enumerators;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public enum Role {
    @JsonValue
    MANAGEMENT,
    ADMIN,
    CUSTOMER,
    USER;

    @JsonValue
    public String toValue() {
        return name();
    }

}
