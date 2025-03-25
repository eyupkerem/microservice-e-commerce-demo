package com.malkoc.orderservice.request;

import com.malkoc.orderservice.costumer.response.CustomerResponse;
import com.malkoc.orderservice.enums.PaymentMethod;
import com.malkoc.orderservice.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse costumer,
        List<PurchaseResponse> products
) {
}
