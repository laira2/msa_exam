package com.sparta.msa_exam.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ResponseEntity<ProductResponseDto> createProduct(ProductRequestDto productRequestDto) {
        Product newProduct = productRepository.save(new Product(productRequestDto));
        return ResponseEntity.ok(ProductResponseDto.createProductResponseDto(newProduct));
    }

    public List<ProductResponseDto> getProducts(Long productId, String name) {
        List<Product> productList = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            productList = productRepository.findAllByName(name);
        } else if (productId != null) {
            productList.add(productRepository.findById(productId).orElseThrow(
                    ()-> new IllegalArgumentException( productId + " 에 해당하는 상품이 없습니다.")
            ));
        }else {
            productList = productRepository.findAll();
        }

        return productList.stream().map(ProductResponseDto::createProductResponseDto)
                .collect(Collectors.toList());
    }


}
