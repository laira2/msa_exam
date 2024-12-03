package com.sparta.msa_exam.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public OrderProduct(Order order, Long productId) {
        this.order = order;
        this.productId = productId;
    }
}
