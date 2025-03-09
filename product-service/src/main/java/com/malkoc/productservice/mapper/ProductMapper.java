package com.malkoc.productservice.mapper;

import com.malkoc.productservice.entity.Category;
import com.malkoc.productservice.entity.Product;
import com.malkoc.productservice.request.ProductRequest;
import com.malkoc.productservice.response.ProductPurchaseResponse;
import com.malkoc.productservice.response.ProductResponse;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();
    }

    public ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice().multiply(java.math.BigDecimal.valueOf(quantity)),
                quantity
        );
    }
}