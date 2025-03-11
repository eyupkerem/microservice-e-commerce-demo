package com.malkoc.costumerservice.mapper;

import com.malkoc.costumerservice.entity.Costumer;
import com.malkoc.costumerservice.request.CostumerRequest;
import com.malkoc.costumerservice.response.CostumerResponse;
import org.springframework.stereotype.Component;

@Component
public class CostumerMapper {
    public Costumer toCostumer(CostumerRequest request) {
        return Costumer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CostumerResponse toResponse(Costumer costumer) {
        return new CostumerResponse(
                costumer.getId(),
                costumer.getFirstname(),
                costumer.getLastname(),
                costumer.getEmail(),
                costumer.getAddress()
        );
    }
}
