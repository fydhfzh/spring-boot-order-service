package com.fydhfzh.order_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fydhfzh.order_service.dto.order.OrderRequest;
import com.fydhfzh.order_service.dto.order.OrderResponse;
import com.fydhfzh.order_service.entity.Order;
import com.fydhfzh.order_service.entity.Product;
import com.fydhfzh.order_service.entity.User;
import com.fydhfzh.order_service.exception.order.OrderNotFoundException;
import com.fydhfzh.order_service.exception.product.InsufficientStockException;
import com.fydhfzh.order_service.exception.product.ProductNotFoundException;
import com.fydhfzh.order_service.exception.user.UserNotFoundException;
import com.fydhfzh.order_service.repository.OrderRepository;
import com.fydhfzh.order_service.repository.ProductRepository;
import com.fydhfzh.order_service.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository,
            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OrderResponse findById(String email, int id) {
        User user = userRepository.findByEmailActive(email).orElseThrow(
                () -> new UserNotFoundException("No user present with email: " + email));

        Order order = orderRepository.findByUserAndIdActive(user, id).orElseThrow(
                () -> new OrderNotFoundException("No order present with id: " + id));

        Product product = order.getProduct();

        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .product(product)
                .build();

        return orderResponse;
    }

    @Override
    public List<OrderResponse> findAll(String email) {
        User user = userRepository.findByEmailActive(email).orElseThrow(
                () -> new UserNotFoundException("No user present with email: " + email));

        List<Order> orders = orderRepository.findAllByUserActive(user);

        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No order present");
        }

        return orders.stream().map(order -> OrderResponse.builder()
                .id(order.getId())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .product(order.getProduct())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OrderResponse save(OrderRequest payload) {
        User user = userRepository.findByEmailActive(payload.getUserEmail()).orElseThrow(
                () -> new UserNotFoundException(
                        "No user present with email: " + payload.getUserEmail()));

        Product product = productRepository.findByIdActive(payload.getProductId()).orElseThrow(
                () -> new ProductNotFoundException(
                        "No product present with id: " + payload.getProductId()));

        int totalPrice = payload.getQuantity() * product.getPricePerItem();

        if (product.getQuantity() < payload.getQuantity() && product.getQuantity() > 0) {
            throw new InsufficientStockException("Remaining product stocks are insufficient, remaining: "
                    + product.getQuantity() + ", requested: " + payload.getQuantity());
        }

        product.setQuantity(product.getQuantity() - payload.getQuantity());
        product.setUpdatedAt(LocalDateTime.now());

        Order order = Order.builder()
                .quantity(payload.getQuantity())
                .totalPrice(totalPrice)
                .user(user)
                .product(product)
                .build();

        order = orderRepository.save(order);

        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .product(product)
                .build();

        return orderResponse;
    }

    @Transactional
    @Override
    public void delete(String email, int id) {
        User user = userRepository.findByEmailActive(email).orElseThrow(
                () -> new UserNotFoundException(
                        "No user present with email: " + email));

        Order order = orderRepository.findByUserAndIdActive(user, id).orElseThrow(
                () -> new OrderNotFoundException(
                        "No order present with id: " + id));
        order.setDeletedAt(LocalDateTime.now());

        orderRepository.save(order);
        return;
    }

}
