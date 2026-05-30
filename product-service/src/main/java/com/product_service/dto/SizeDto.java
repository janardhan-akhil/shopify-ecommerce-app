package com.product_service.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.product_service.entity.Brand;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SizeDto {

    private Integer id;
    private String size;
    private String quantity;

    private BrandDto brand;
}
