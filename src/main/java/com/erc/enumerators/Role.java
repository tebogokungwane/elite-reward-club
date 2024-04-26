package com.erc.enumerators;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public enum Role {
    @JsonValue
    MANAGEMENT,
    ADMIN,
    CUSTOMER;

}
