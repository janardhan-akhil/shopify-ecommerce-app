package com.order_service.controller;

import com.order_service.entity.Order;
import com.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestHeader("X-CART-ID") String uuid) {
        Order order = orderService.createOrder(uuid);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}")
    public boolean updateOrderStatus(@PathVariable Long orderId) {
       return orderService.updateOrderStatus(orderId);
    }
}
