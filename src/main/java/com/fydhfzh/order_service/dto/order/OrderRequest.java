package com.fydhfzh.order_service.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @NotBlank
    @Min(value = 1)
    private int quantity;
    @NotNull
    @Min(value = 0)
    private int productId;
    @JsonIgnore
    private String userEmail;
}
