package com.orderserviceexample.controller;

import com.orderserviceexample.dto.OrderEvent;
import com.orderserviceexample.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer producer;

    @PostMapping
    public String placeOrder(@RequestBody OrderEvent event) {
        producer.sendOrderEvent(event);
        return "Order Event Published Successfully";
    }
}

