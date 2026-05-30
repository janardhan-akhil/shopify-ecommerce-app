package com.cart_service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartDto {

    private Long id;
    private String uuid;
    private Long userId;

    private List<CartItemDto> items = new ArrayList<>();
}
