package com.product_service.mapper;

import com.product_service.dto.CategoryDto;
import com.product_service.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public CategoryDto mapToDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }

    public Category mapToEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }
}
