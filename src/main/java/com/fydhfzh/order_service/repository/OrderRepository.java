package com.fydhfzh.order_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fydhfzh.order_service.entity.Order;
import com.fydhfzh.order_service.entity.User;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.user = :user AND o.deletedAt IS NULL")
    List<Order> findAllByUserActive(User user);

    @Query("SELECT o FROM Order o WHERE o.id = :orderId AND o.user = :user AND o.deletedAt IS NULL")
    Optional<Order> findByUserAndIdActive(User user, int orderId);
}
