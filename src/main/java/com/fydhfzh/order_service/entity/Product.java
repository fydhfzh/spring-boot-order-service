package com.fydhfzh.order_service.entity;

import java.util.List;

import com.fydhfzh.order_service.dto.product.ProductResponse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    // define fields
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price_per_item")
    private int pricePerItem;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH
    }, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Order> orders;

    // define constructors
    public Product() {

    }

    public Product(String name, String description, int pricePerItem, int quantity) {
        super();
        this.name = name;
        this.description = description;
        this.pricePerItem = pricePerItem;
        this.quantity = quantity;
    }

    // define setter/getter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(int pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", description=" + description + ", pricePerItem=" + pricePerItem
                + ", quantity=" + quantity + ", getId()=" + getId() + "]";
    }

    public ProductResponse toProductResponse() {
        return new ProductResponse(this.getId(), this.name, this.description, this.pricePerItem, this.quantity,
                this.getCreatedAt(), this.getUpdatedAt());
    }
}
