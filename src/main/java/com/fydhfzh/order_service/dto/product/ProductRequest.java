package com.fydhfzh.order_service.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
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
}
