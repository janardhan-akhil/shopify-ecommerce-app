package service.test;

import com.product_service.dto.CategoryDto;
import com.product_service.entity.Category;
import com.product_service.mapper.CategoryMapper;
import com.product_service.repository.CategoryRepository;
import com.product_service.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    @InjectMocks
    private CategoryServiceImpl categoryService;


    @Test
    void shouldReturnAllCategories() {

        // Arrange
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Electronics");

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Books");

        List<Category> categories = List.of(category1, category2);

        CategoryDto dto1 = new CategoryDto();
        dto1.setId(1);
        dto1.setName("Electronics");

        CategoryDto dto2 = new CategoryDto();
        dto2.setId(2);
        dto2.setName("Books");

        when(categoryRepository.findAll()).thenReturn(categories);

        when(categoryMapper.mapToDto(category1)).thenReturn(dto1);
        when(categoryMapper.mapToDto(category2)).thenReturn(dto2);

        // Act
        List<CategoryDto> result = categoryService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("Electronics", result.get(0).getName());
        assertEquals("Books", result.get(1).getName());

        verify(categoryRepository, times(1)).findAll();
        verify(categoryMapper, times(1)).mapToDto(category1);
        verify(categoryMapper, times(1)).mapToDto(category2);
    }
}
