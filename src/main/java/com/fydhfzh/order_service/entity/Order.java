package com.fydhfzh.order_service.entity;

import com.fydhfzh.order_service.dto.order.OrderResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    // extends BaseEntity
    // define fields
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Order() {
    }

    // define constructor
    public Order(int quantity, int totalPrice, User user, Product product) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.user = user;
        this.product = product;
    }

    // define getter/setter
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order [quantity=" + quantity + ", totalPrice=" + totalPrice + ", getId()=" + getId() + "]";
    }

    public OrderResponse toOrderResponse() {
        return new OrderResponse(this.getId(), this.quantity, this.totalPrice, this.getProduct().toProductResponse());
    }
}
