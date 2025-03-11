package com.malkoc.orderservice.mapper;

import com.malkoc.orderservice.entity.Order;
import com.malkoc.orderservice.request.OrderRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest request){
        return Order.builder()
                .id(request.id())
                .costumerId(request.costumerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

}
