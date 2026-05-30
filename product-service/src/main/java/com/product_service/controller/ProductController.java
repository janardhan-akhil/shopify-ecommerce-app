package com.product_service.controller;

import com.product_service.dto.CategoryDto;
import com.product_service.dto.ProductDto;
import com.product_service.service.CategoryService;
import com.product_service.service.ProductService;
import com.product_service.service.S3Service;
import com.product_service.utility.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final S3Service s3Service;

    public ProductController(ProductService productService, CategoryService categoryService, S3Service s3Service) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.s3Service = s3Service;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addProduct(){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData("Product added successfully");
        apiResponse.setMessage("success");
        apiResponse.setSuccess(true);
        apiResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDto>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.findAll()));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.findByCategoryId(id)));
    }

    @GetMapping("/categories/name")
    public ResponseEntity<ApiResponse<CategoryDto>> findByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categoryService.findByCategoryName(name)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductDto>>> searchProducts(@RequestParam String keyword) {
        List<ProductDto> products = productService.searchProducts(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(products));
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
        List<String> fileUrls = s3Service.uploadFiles(files);
        return ResponseEntity.ok(fileUrls);
    }


}
