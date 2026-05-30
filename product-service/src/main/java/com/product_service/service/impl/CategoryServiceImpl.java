package com.product_service.service.impl;

import com.product_service.dto.CategoryDto;
import com.product_service.entity.Category;
import com.product_service.exception.ResourceNotFoundException;
import com.product_service.mapper.CategoryMapper;
import com.product_service.repository.CategoryRepository;
import com.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapperObject;


    @Override
    public List<CategoryDto> findAll() {
        List<Category> all = categoryRepository.findAll();
        return all.stream().map(mapperObject::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findByCategoryId(Integer id) {
        return mapperObject.mapToDto(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found with id: " + id)));
    }

    @Override
    public CategoryDto findByCategoryName(String name) {
        return mapperObject.mapToDto(categoryRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Category Not Found with name: " + name)));
    }
}
