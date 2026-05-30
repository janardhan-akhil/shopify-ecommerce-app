package com.product_service.dto;


import com.product_service.entity.Brand;
import com.product_service.entity.SubCategory;

import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductDto {

    private Integer id;
    private String name;
    private BigDecimal price;

    private String subCategory;

    private Set<BrandDto> brands =
            new LinkedHashSet<>();
}
