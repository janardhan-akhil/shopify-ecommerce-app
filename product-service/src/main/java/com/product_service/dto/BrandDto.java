package com.product_service.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.product_service.entity.Image;
import com.product_service.entity.Product;
import com.product_service.entity.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BrandDto {

    private Integer id;
    private String name;
    private Double price;

    private Set<String> sizes =
            new LinkedHashSet<>();

    private Set<String> images =
            new LinkedHashSet<>();


}
