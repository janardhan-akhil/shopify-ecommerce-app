package com.product_service.service;

import com.product_service.dto.ProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> searchProducts(String keyword);
}
