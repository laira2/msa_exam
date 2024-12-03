package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.core.ProductClient;
import com.sparta.msa_exam.order.core.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;


    public ResponseEntity<OrderResponseDto> createOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        List<ProductResponseDto> products = new ArrayList<>();
        for (Long productId : orderRequestDto.getProductsIdList()) {
            for (ProductResponseDto product:productClient.getProducts(productId) ) {
                products.add(product);
            }
            OrderProduct orderProduct = new OrderProduct(order, productId);
            order.getProducts_id().add(orderProduct);
        }
        orderRepository.save(order);
        return ResponseEntity.ok(OrderResponseDto.createOrderResponseDto(order,products));
    }

    @Transactional
    public ResponseEntity<OrderResponseDto> updateOrder(Long orderId, List<Long> productIds) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException("Order not found")
        );

        List<ProductResponseDto> products = new ArrayList<>();
//        기존 주문 products 조회
        for (OrderProduct orderProduct : order.getProducts_id()) {
            List<ProductResponseDto> productResponseDto = productClient.getProducts(orderProduct.getProductId());
            for (ProductResponseDto product:productResponseDto) {
                products.add(product);
            }
        }
//        새로 입력된 products 조회 후 추가
        for (Long productId : productIds) {
            for (ProductResponseDto product:productClient.getProducts(productId) ) {
                products.add(product);
            }
            OrderProduct orderProduct = new OrderProduct(order, productId);
            order.getProducts_id().add(orderProduct);
        }

        return ResponseEntity.ok(OrderResponseDto.createOrderResponseDto(order, products));
    }

    public ResponseEntity<OrderResponseDto> getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new IllegalArgumentException("해당하는 Id 값을 가진 주문이 없습니다."));
        List<ProductResponseDto> products = new ArrayList<>();

        for (OrderProduct orderProduct : order.getProducts_id()) {
//          product가 존재하는지 확인
            List<ProductResponseDto> productResponseDto = productClient.getProducts(orderProduct.getProductId());
            for (ProductResponseDto product:productResponseDto) {
                products.add(product);
            }
        }
        return ResponseEntity.ok(OrderResponseDto.createOrderResponseDto(order,products));

    }
}