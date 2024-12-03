package com.sparta.msa_exam.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_id;

    private String name;

    private int supply_price;

    public Product(ProductRequestDto productRequestDto) {
        this.name = productRequestDto.getName();
        this.supply_price = productRequestDto.getSupply_price();
    }
}
