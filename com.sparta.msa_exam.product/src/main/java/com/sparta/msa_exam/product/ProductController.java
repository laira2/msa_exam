package com.sparta.msa_exam.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(@RequestParam(required = false) Long productId
                                                                ,@RequestParam(required = false) String name) {
        return ResponseEntity.ok(productService.getProducts(productId, name));
    }

}
