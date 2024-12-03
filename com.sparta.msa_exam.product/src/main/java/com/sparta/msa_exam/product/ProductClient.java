package com.sparta.msa_exam.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    ResponseEntity<List<ProductResponseDto>> getProducts(@RequestParam(required = false) Integer productId
            , @RequestParam(required = false) String name);

    @PostMapping("/products")
    ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto product);
}
