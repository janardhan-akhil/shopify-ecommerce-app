package com.order_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {

    private Long productId;
    private Long brandId;
    private Integer quantity;
    private BigDecimal price;
}
