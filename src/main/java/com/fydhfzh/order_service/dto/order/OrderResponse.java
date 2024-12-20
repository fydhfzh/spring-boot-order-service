package com.fydhfzh.order_service.dto.order;

import com.fydhfzh.order_service.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString(exclude = "product")
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int id;
    private int quantity;
    private int totalPrice;
    private Product product;
}
