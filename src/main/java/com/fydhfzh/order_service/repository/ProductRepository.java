package com.fydhfzh.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fydhfzh.order_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
