package com.sparta.msa_exam.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private Long product_id;

    private String name;

    private int supply_price;



    public static ProductResponseDto createProductResponseDto(Product product) {
        return new ProductResponseDto(
                product.getProduct_id(),
                product.getName(),
                product.getSupply_price()
        );
    }
}
