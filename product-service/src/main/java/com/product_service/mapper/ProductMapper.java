package com.product_service.mapper;

import com.product_service.dto.BrandDto;
import com.product_service.dto.ProductDto;
import com.product_service.entity.Brand;
import com.product_service.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {

//    public static final ModelMapper mapper = new ModelMapper();
//
//    public  ProductDto mapToDto(Product product) {
//        return mapper.map(product, ProductDto.class);
//    }
//
//    public static Product mapToEntity(ProductDto productDto) {
//        return mapper.map(productDto, Product.class);
//    }


    public ProductDto
    mapToDto(Product product) {

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .subCategory(product.getSubCategory().getName())
                .brands(product.getBrands()
                                .stream()
                                .map(ProductMapper::mapBrandDto)
                                .collect(Collectors.toSet()))
                .build();
    }

    private static BrandDto
    mapBrandDto(Brand brand) {

        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .price(brand.getPrice())
                .sizes(brand.getSizes()
                                .stream()
                                .map(size -> size.getSize())
                                .collect(Collectors.toSet()))
                .images(brand.getImages()
                                .stream()
                                .map(image -> image.getUrl())
                                .collect(Collectors.toSet()))
                .build();
    }
}
