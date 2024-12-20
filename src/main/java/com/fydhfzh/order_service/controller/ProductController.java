package com.fydhfzh.order_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fydhfzh.order_service.dto.ApiResponse;
import com.fydhfzh.order_service.dto.product.AddQuantityProductRequest;
import com.fydhfzh.order_service.dto.product.ProductRequest;
import com.fydhfzh.order_service.dto.product.ProductResponse;
import com.fydhfzh.order_service.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> all() {
        List<ProductResponse> productsResponse = productService.findAll();

        ApiResponse response = ApiResponse.builder()
                .status("success")
                .statusCode(HttpStatus.OK.value())
                .message("Products retrieved successfully")
                .data(productsResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody ProductRequest productPayload) {
        ProductResponse productResponse = productService.save(productPayload);

        ApiResponse response = ApiResponse.builder()
                .status("success")
                .statusCode(HttpStatus.CREATED.value())
                .message("Product created successfully")
                .data(productResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> update(@RequestBody ProductRequest payload) {
        ProductResponse productResponse = productService.update(payload);

        ApiResponse response = ApiResponse.builder()
                .status("success")
                .statusCode(HttpStatus.OK.value())
                .message("Product updated successfully")
                .data(productResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> one(@PathVariable int id) {
        ProductResponse productResponse = productService.findById(id);

        ApiResponse response = ApiResponse.builder()
                .status("success")
                .statusCode(HttpStatus.OK.value())
                .message("Product retrieved successfully")
                .data(productResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable int id) {
        productService.delete(id);

        ApiResponse response = ApiResponse.builder()
                .status("success")
                .statusCode(HttpStatus.OK.value())
                .message("Product deleted successfully")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/add-quantity")
    public ResponseEntity<ApiResponse> addQuantity(@RequestBody AddQuantityProductRequest payload,
            @PathVariable int id) {
        ProductResponse productResponse = productService.updateQuantityById(payload, id);

        ApiResponse response = ApiResponse.builder()
                .status("success")
                .statusCode(HttpStatus.OK.value())
                .message("Product updated successfully")
                .data(productResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
