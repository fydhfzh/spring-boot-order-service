package com.fydhfzh.order_service.dto.product;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int id;
    private String name;
    private String description;
    private int pricePerItem;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
