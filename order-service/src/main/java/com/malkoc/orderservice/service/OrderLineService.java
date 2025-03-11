package com.malkoc.orderservice.service;

import com.malkoc.orderservice.mapper.OrderLineMapper;
import com.malkoc.orderservice.repository.OrderLineRepository;
import com.malkoc.orderservice.request.OrderLineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }
}
