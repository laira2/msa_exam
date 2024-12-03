package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.core.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long order_id;
    private List<ProductResponseDto> products;
    private String message;

    public OrderResponseDto(String s) {
        this.message = s;
        this.order_id = null;
        this.products = null;
    }

    public static OrderResponseDto createOrderResponseDto(Order order,List<ProductResponseDto> products) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.order_id = order.getOrder_id();
        dto.products = products;
        dto.message =null;

        return dto;
    }
}
