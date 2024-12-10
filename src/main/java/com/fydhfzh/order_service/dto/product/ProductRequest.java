package com.fydhfzh.order_service.dto.product;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fydhfzh.order_service.entity.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductRequest {
    @NotBlank
    @Size(min = 4)
    private String name;
    @NotBlank
    @Size(min = 4)
    private String description;
    @Min(value = 0)
    private int pricePerItem;
    @Min(value = 0)
    private int quantity;

    public ProductRequest(String name, String description, int pricePerItem, int quantity) {
        this.name = name;
        this.description = description;
        this.pricePerItem = pricePerItem;
        this.quantity = quantity;
    }

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

    public Product toProduct() {
        return new Product(this.name, this.description, this.pricePerItem, this.quantity);
    }
}
