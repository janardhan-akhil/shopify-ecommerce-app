package com.payment_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "order-service", url = "http://localhost:8083/api/v1/orders")
public interface OrderFeignClient {

    @PutMapping("/{orderId}")
    public boolean updateOrderStatus(@PathVariable Long orderId);
}
