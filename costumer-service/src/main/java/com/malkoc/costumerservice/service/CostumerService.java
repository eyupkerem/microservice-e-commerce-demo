package com.malkoc.costumerservice.service;

import com.malkoc.costumerservice.entity.Costumer;
import com.malkoc.costumerservice.exception.CostumerNotFoundException;
import com.malkoc.costumerservice.mapper.CostumerMapper;
import com.malkoc.costumerservice.repository.CostumerRepository;
import com.malkoc.costumerservice.request.CostumerRequest;
import com.malkoc.costumerservice.response.CostumerResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostumerService {

    private final CostumerRepository costumerRepository;
    private final CostumerMapper costumerMapper;

    public String create(CostumerRequest request) {
        Costumer costumer = costumerRepository.save(costumerMapper.toCostumer(request));
        return costumer.getId();
    }

    public Void update(CostumerRequest request) {
        Costumer costumer = costumerRepository.findById(request.id()).orElseThrow(
                ()-> new CostumerNotFoundException(String.format("CANNOT UPDATE COSTUMER WİTH ID : " + request.id()))
        );
        mergerCostumer(costumer,request);
        costumerRepository.save(costumer);
        return null;
    }

    private void mergerCostumer(Costumer costumer, CostumerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())){
            costumer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())){
            costumer.setLastname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())){
            costumer.setEmail(request.email());
        }
        if (request.address() != null){
            costumer.setAddress(request.address());
        }
    }

    public List<CostumerResponse> findAll() {
        return costumerRepository.findAll()
                .stream()
                .map(costumerMapper::toResponse)
                .toList();
    }

    public Boolean existById(String costumerId) {
        return costumerRepository.findById(costumerId)
                .isPresent();
    }

    public CostumerResponse findById(String costumerId) {
        return costumerRepository.findById(costumerId)
                .map(costumerMapper::toResponse)
                .orElseThrow(
                () -> new CostumerNotFoundException("COSTUMER CANNOT FOUND WITH ID::" + costumerId)
        );
    }

    public void deleteById(String costumerId) {
        costumerRepository.deleteById(costumerId);
    }
}
