package com.sparta.msa_exam.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto,
                                                        @RequestParam(required = false, defaultValue = "false") boolean fail) {
        return orderService.createOrder(orderRequestDto, fail);
    }


    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long orderId,
                                                        @RequestBody OrderRequestDto orderRequestDto) {
        System.out.println("=========orderId : " +orderId + "=========");
        System.out.println("=========orderRequestDto : " +orderRequestDto.getProductsIdList() + "=========");
        return orderService.updateOrder(orderId, orderRequestDto.getProductsIdList());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }
}
