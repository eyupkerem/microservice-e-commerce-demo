package com.malkoc.productservice.service;

import com.malkoc.productservice.entity.Product;
import com.malkoc.productservice.exception.ProductPurchaseException;
import com.malkoc.productservice.mapper.ProductMapper;
import com.malkoc.productservice.repository.ProductRepository;
import com.malkoc.productservice.request.ProductPurchaseRequest;
import com.malkoc.productservice.request.ProductRequest;
import com.malkoc.productservice.response.ProductPurchaseResponse;
import com.malkoc.productservice.response.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest request) {
        Product product = productRepository.save(productMapper.toEntity(request));
        return product.getId();
    }

    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> productIds = extractProductIds(request);
        List<Product> storedProducts = fetchAndValidateProducts(productIds);
        List<ProductPurchaseRequest> sortedRequest = sortRequestsById(request);

        return processProductPurchases(storedProducts, sortedRequest);
    }

    private List<Integer> extractProductIds(List<ProductPurchaseRequest> request) {
        return request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
    }

    private List<Product> fetchAndValidateProducts(List<Integer> productIds) {
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        return storedProducts;
    }

    private List<ProductPurchaseRequest> sortRequestsById(List<ProductPurchaseRequest> request) {
        return request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
    }

    private List<ProductPurchaseResponse> processProductPurchases(
            List<Product> storedProducts, List<ProductPurchaseRequest> sortedRequest) {
        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();
        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = sortedRequest.get(i);
            validateStockAvailability(product, productRequest);
            updateProductStock(product, productRequest.quantity());
            purchasedProducts.add(productMapper.toproductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    private void validateStockAvailability(Product product, ProductPurchaseRequest productRequest) {
        if (product.getAvailableQuantity() < productRequest.quantity()) {
            throw new ProductPurchaseException(
                    "Insufficient stock quantity for product with ID: " + productRequest.productId()
            );
        }
    }

    private void updateProductStock(Product product, double quantity) {
        product.setAvailableQuantity(product.getAvailableQuantity() - quantity);
        productRepository.save(product);
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found wiht ID :: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
