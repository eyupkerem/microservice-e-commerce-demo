package com.malkoc.costumerservice.controller;

import com.malkoc.costumerservice.entity.Costumer;
import com.malkoc.costumerservice.request.CostumerRequest;
import com.malkoc.costumerservice.response.CostumerResponse;
import com.malkoc.costumerservice.service.CostumerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/costumer")
public class CostumerController {

    private final CostumerService costumerService;

    @PostMapping
    public ResponseEntity<String> createCostumer(@RequestBody @Valid CostumerRequest request){
        return ResponseEntity.ok(costumerService.create(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCostumer(@RequestBody @Valid CostumerRequest request){
        return ResponseEntity.ok(costumerService.update(request));
    }

    @GetMapping
    public ResponseEntity<List<CostumerResponse>> findAll(){
        return ResponseEntity.ok(costumerService.findAll());
    }

    @GetMapping("/exist/{costumer-id}")
    public ResponseEntity<Boolean> existById(@PathVariable("costumer-id") String costumerId){
        return ResponseEntity.ok(costumerService.existById(costumerId));
    }

    @GetMapping("/{costumer-id}")
    public ResponseEntity<CostumerResponse> findById(@PathVariable("costumer-id") String costumerId){
        return ResponseEntity.ok(costumerService.findById(costumerId));
    }

    @DeleteMapping("/{costumer-id}")
    public ResponseEntity<Void> deleteById(@PathVariable("costumer-id") String costumerId) {
        costumerService.deleteById(costumerId);
        return ResponseEntity.noContent().build();
    }


}

