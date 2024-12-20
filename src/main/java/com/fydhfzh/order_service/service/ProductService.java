package com.fydhfzh.order_service.service;

import java.util.List;

import com.fydhfzh.order_service.dto.product.AddQuantityProductRequest;
import com.fydhfzh.order_service.dto.product.ProductRequest;
import com.fydhfzh.order_service.dto.product.ProductResponse;

public interface ProductService {
    ProductResponse save(ProductRequest payload);

    ProductResponse update(ProductRequest payload);

    ProductResponse findById(int id);

    List<ProductResponse> findAll();

    void delete(int id);

    ProductResponse updateQuantityById(AddQuantityProductRequest payload, int id);
}
