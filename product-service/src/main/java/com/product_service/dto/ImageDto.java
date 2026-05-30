package com.product_service.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.product_service.entity.Brand;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ImageDto {

    private Integer id;
    private String url;

    private BrandDto brand;
}
