package com.order_service.client;

import com.order_service.dto.CartResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CartClientFallback
        implements CartFeignClient {

    @Override
    public CartResponse getCart(String uuid) {
        CartResponse response = new CartResponse();
        response.setUuid(uuid);
        response.setUserId(null);
        response.setItems(new ArrayList<>());
        return response;
    }

    @Override
    public void clearCart(String uuid) {
        System.err.println("Failed to clear cart for UUID: " + uuid);
    }
}