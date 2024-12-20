package com.fydhfzh.order_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String status;
    private String message;
    private int statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

}
