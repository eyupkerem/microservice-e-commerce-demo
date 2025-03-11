package com.malkoc.orderservice.costumer.response;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email

) {
}
