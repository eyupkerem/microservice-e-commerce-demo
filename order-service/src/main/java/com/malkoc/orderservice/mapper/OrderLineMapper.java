package com.malkoc.orderservice.mapper;

import com.malkoc.orderservice.entity.Order;
import com.malkoc.orderservice.order_line.OrderLine;
import com.malkoc.orderservice.request.OrderLineRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.orderId())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder().
                                id(orderLineRequest.orderId())
                                .build())
                .productId(orderLineRequest.productId())
                .build();
    }
}
