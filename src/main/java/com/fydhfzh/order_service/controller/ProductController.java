package com.fydhfzh.order_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @CrossOrigin
    public ResponseEntity<ApiResponse> all() {
        List<ProductResponse> productsResponse = productService.findAll();

        ApiResponse response = new ApiResponse(
                "success",
                "Products retrieved successfully",
                200,
                productsResponse);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody ProductRequest productPayload) {
        ProductResponse productResponse = productService.save(productPayload);

        ApiResponse response = new ApiResponse("success",
                "Product created successfully", 201, productResponse);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody ProductRequest payload, @PathVariable int id) {
        ProductResponse productResponse = productService.update(payload, id);

        ApiResponse response = new ApiResponse("success", "Product updated successfully", 200, productResponse);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> one(@PathVariable int id) {
        ProductResponse productResponse = productService.findById(id);

        ApiResponse response = new ApiResponse("success",
                "Product retrieved successfully", 200, productResponse);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable int id) {
        productService.delete(id);

        ApiResponse response = new ApiResponse("success", "Product deleted successfully", 200);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-quantity")
    public ResponseEntity<ApiResponse> addQuantity(@RequestBody AddQuantityProductRequest payload,
            @PathVariable int id) {
        ProductResponse productResponse = productService.updateQuantityById(payload, id);

        ApiResponse response = new ApiResponse("success", "Product quantity successfully increased", 200,
                productResponse);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }
}
