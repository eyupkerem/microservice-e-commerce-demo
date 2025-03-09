package com.malkoc.productservice.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Description is required")
        String description,
        @Positive(message = "Price should be positive")
        BigDecimal price,
        @Positive(message = "Quantity should be positive")
        double availableQuantity,
        @NotNull(message = "Category is required")
        Integer categoryId
) {}
