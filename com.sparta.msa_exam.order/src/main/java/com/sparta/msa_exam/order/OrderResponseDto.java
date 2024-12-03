package com.sparta.msa_exam.order;

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
    private List<Long> products_id;

    public static OrderResponseDto createOrderResponseDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.order_id = order.getOrder_id();
        dto.products_id = order.getProducts_id().stream()
                .map(OrderProduct::getProductId)
                .collect(Collectors.toList());
        return dto;
    }
}
