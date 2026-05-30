package com.cart_service.dto;

import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddToCartRequest {

    private Long productId;
    private Long brandId;
    private Integer quantity;
    private BigDecimal price;
}
