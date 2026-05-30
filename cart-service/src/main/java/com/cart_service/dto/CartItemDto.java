package com.cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartItemDto {

    private Long id;
    private Long productId;
    private Long brandId;
    private Integer quantity;
    private BigDecimal price;
    private CartDto cartDto;
}
