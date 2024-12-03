package com.sparta.msa_exam.order.core;

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
}