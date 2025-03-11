package com.malkoc.costumerservice.response;

import com.malkoc.costumerservice.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CostumerResponse (
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
){
}
