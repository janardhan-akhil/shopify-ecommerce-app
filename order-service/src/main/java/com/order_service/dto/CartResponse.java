package com.order_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {

    private String uuid;
    private Long userId;
    private List<CartItemResponse> items;
}
