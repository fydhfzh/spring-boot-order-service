package com.fydhfzh.order_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fydhfzh.order_service.dto.product.AddQuantityProductRequest;
import com.fydhfzh.order_service.dto.product.ProductRequest;
import com.fydhfzh.order_service.dto.product.ProductResponse;
import com.fydhfzh.order_service.entity.Product;
import com.fydhfzh.order_service.exception.NoSuchProductExists;
import com.fydhfzh.order_service.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public ProductResponse save(ProductRequest payload) {
        Product product = payload.toProduct();

        Product newProduct = productRepository.save(product);

        return newProduct.toProductResponse();
    }

    @Override
    public ProductResponse findById(int id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NoSuchProductExists(
                        "No product present with id: " + id));

        return product.toProductResponse();
    }

    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> product.toProductResponse()).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductResponse update(ProductRequest payload, int productId) {
        Product oldProduct = productRepository.findById(productId).orElseThrow(
                () -> new NoSuchProductExists(
                        "No product present with id: " + productId));

        Product updatedProduct = payload.toProduct();

        updatedProduct.setId(productId);
        updatedProduct.setCreatedAt(oldProduct.getCreatedAt());
        updatedProduct.setUpdatedAt(LocalDateTime.now());

        updatedProduct = productRepository.save(updatedProduct);

        return updatedProduct.toProductResponse();
    }

    @Transactional
    @Override
    public void delete(int id) {
        productRepository.findById(id).orElseThrow(
                () -> new NoSuchProductExists(
                        "No product present with id: " + id));

        productRepository.deleteById(id);
        return;
    }

    @Transactional
    @Override
    public ProductResponse updateQuantityById(AddQuantityProductRequest payload, int id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NoSuchProductExists(
                        "No product present with id: " + id));

        product.setQuantity(product.getQuantity() + payload.getQuantity());

        product = productRepository.save(product);

        return product.toProductResponse();
    }

}
