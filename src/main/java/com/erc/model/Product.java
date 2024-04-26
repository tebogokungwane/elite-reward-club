package com.erc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @NotNull(message = "Id should not be null")
    private Long id;
    private String name;
    @NotNull
    private double price;
    private int quantity;
    private String productType;
}
