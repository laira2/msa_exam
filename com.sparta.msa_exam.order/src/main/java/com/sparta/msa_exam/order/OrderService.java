package com.sparta.msa_exam.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public ResponseEntity<OrderResponseDto> createOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        for (Long productId : orderRequestDto.getProductsIdList()) {
//            product에 존재하는지 확인
            OrderProduct orderProduct = new OrderProduct(order, productId);
            order.getProducts_id().add(orderProduct);
        }
        orderRepository.save(order);
        return ResponseEntity.ok(OrderResponseDto.createOrderResponseDto(order));
    }

    public ResponseEntity<OrderResponseDto> updateOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException("Order not found")
        );

//        productId에 해당하는 product가 있는지 확인
        order.getProducts_id().add(new OrderProduct(order, productId));
        return ResponseEntity.ok(OrderResponseDto.createOrderResponseDto(order));
    }

    public ResponseEntity<OrderResponseDto> getOrder(Long orderId) {
        return ResponseEntity.ok(OrderResponseDto.createOrderResponseDto(orderRepository.findById(orderId).orElseThrow(
                ()-> new IllegalArgumentException("해당하는 Id 값을 가진 주문이 없습니다."))));

    }
}
