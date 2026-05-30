package com.product_service.dto;

import com.product_service.entity.Category;
import com.product_service.entity.Product;
import lombok.*;


import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SubCategoryDto {

    private Integer id;
    private String name;


    private String category;

    private Set<ProductDto> products = new LinkedHashSet<>();
}
