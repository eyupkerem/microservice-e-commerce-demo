package com.malkoc.orderservice.service;

import com.malkoc.orderservice.costumer.CustomerClient;
import com.malkoc.orderservice.exception.BusinessException;
import com.malkoc.orderservice.mapper.OrderMapper;
import com.malkoc.orderservice.product.ProductClient;
import com.malkoc.orderservice.repository.OrderRepository;
import com.malkoc.orderservice.request.OrderLineRequest;
import com.malkoc.orderservice.request.OrderRequest;
import com.malkoc.orderservice.request.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest request) {

        //check the costumer --> open feign
        var customer = this.customerClient.findCustomerById(request.costumerId()).orElseThrow(
                () -> new BusinessException("Connet create order :: No customer exist ")
        );

        // purchase product -> product microservice

        this.productClient.purchaseProducts(request.products());

        // persist order

        var order = this.orderRepository.save(orderMapper.toOrder(request));

        // persist order line

        for (PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment process

        // send the order confirmation -> notification microservice (kafka)

        return null;
    }
}
