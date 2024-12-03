package com.sparta.msa_exam.order.core;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductResponseDto> getProducts(@RequestParam Long productId);

}