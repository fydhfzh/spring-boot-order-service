package com.fydhfzh.order_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fydhfzh.order_service.dto.product.AddQuantityProductRequest;
import com.fydhfzh.order_service.dto.product.ProductRequest;
import com.fydhfzh.order_service.dto.product.ProductResponse;
import com.fydhfzh.order_service.entity.Product;
import com.fydhfzh.order_service.exception.product.ProductNotFoundException;
import com.fydhfzh.order_service.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
        private ProductRepository productRepository;

        public ProductServiceImpl(ProductRepository productRepository) {
                this.productRepository = productRepository;
        }

        @Transactional
        @Override
        public ProductResponse save(ProductRequest payload) {
                Product product = Product.builder()
                                .name(payload.getName())
                                .description(payload.getDescription())
                                .pricePerItem(payload.getPricePerItem())
                                .quantity(payload.getQuantity())
                                .build();

                Product newProduct = productRepository.save(product);

                ProductResponse productResponse = ProductResponse.builder()
                                .id(newProduct.getId())
                                .name(newProduct.getName())
                                .description(newProduct.getDescription())
                                .pricePerItem(newProduct.getPricePerItem())
                                .quantity(newProduct.getQuantity())
                                .createdAt(newProduct.getCreatedAt())
                                .updatedAt(newProduct.getUpdatedAt())
                                .build();

                return productResponse;
        }

        @Override
        public ProductResponse findById(int id) {
                Product product = productRepository.findByIdActive(id).orElseThrow(
                                () -> new ProductNotFoundException(
                                                "No product present with id: " + id));

                ProductResponse productResponse = ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .description(product.getDescription())
                                .pricePerItem(product.getPricePerItem())
                                .quantity(product.getQuantity())
                                .createdAt(product.getCreatedAt())
                                .updatedAt(product.getUpdatedAt())
                                .build();

                return productResponse;
        }

        @Override
        public List<ProductResponse> findAll() {
                List<Product> products = productRepository.findAllActive();

                return products.stream().map(product -> ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .description(product.getDescription())
                                .pricePerItem(product.getPricePerItem())
                                .quantity(product.getQuantity())
                                .createdAt(product.getCreatedAt())
                                .updatedAt(product.getUpdatedAt())
                                .build()).collect(Collectors.toList());
        }

        @Transactional
        @Override
        public ProductResponse update(ProductRequest payload) {
                Product product = productRepository.findByIdActive(payload.getId()).orElseThrow(
                                () -> new ProductNotFoundException(
                                                "No product present with id: " + payload.getId()));

                product.setName(payload.getName());
                product.setDescription(payload.getDescription());
                product.setPricePerItem(payload.getPricePerItem());
                product.setQuantity(payload.getQuantity());

                Product updatedProduct = productRepository.save(product);

                ProductResponse productResponse = ProductResponse.builder()
                                .id(updatedProduct.getId())
                                .name(updatedProduct.getName())
                                .description(updatedProduct.getDescription())
                                .pricePerItem(updatedProduct.getPricePerItem())
                                .quantity(updatedProduct.getQuantity())
                                .createdAt(updatedProduct.getCreatedAt())
                                .updatedAt(updatedProduct.getUpdatedAt())
                                .build();

                return productResponse;
        }

        @Transactional
        @Override
        public void delete(int id) {
                Product product = productRepository.findByIdActive(id).orElseThrow(
                                () -> new ProductNotFoundException(
                                                "No product present with id: " + id));

                product.setDeletedAt(LocalDateTime.now());

                productRepository.save(product);
                return;
        }

        @Transactional
        @Override
        public ProductResponse updateQuantityById(AddQuantityProductRequest payload, int id) {
                Product product = productRepository.findByIdActive(id).orElseThrow(
                                () -> new ProductNotFoundException(
                                                "No product present with id: " + id));

                product.setQuantity(product.getQuantity() + payload.getQuantity());

                product = productRepository.save(product);

                ProductResponse productResponse = ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .description(product.getDescription())
                                .pricePerItem(product.getPricePerItem())
                                .quantity(product.getQuantity())
                                .createdAt(product.getCreatedAt())
                                .updatedAt(product.getUpdatedAt())
                                .build();

                return productResponse;
        }

}
