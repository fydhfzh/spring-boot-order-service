package com.fydhfzh.order_service.service;

import java.util.List;

import com.fydhfzh.order_service.dto.order.OrderRequest;
import com.fydhfzh.order_service.dto.order.OrderResponse;

public interface OrderService {
    OrderResponse findById(String email, int id);

    List<OrderResponse> findAll(String email);

    OrderResponse save(OrderRequest payload);

    void delete(String email, int id);
}
