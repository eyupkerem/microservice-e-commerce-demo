package com.malkoc.costumerservice.request;

import com.malkoc.costumerservice.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CostumerRequest(
         String id,
         @NotNull(message = "Costumer first name cannot be null")
         String firstname,
         @NotNull(message = "Costumer last name cannot be null")
         String lastname,
         @NotNull(message = "Costumer email cannot be null")
         @Email(message = "Please enter a valid email")
         String email,
         Address address
) {
}
