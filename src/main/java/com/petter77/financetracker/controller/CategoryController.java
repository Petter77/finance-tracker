package com.petter77.financetracker.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.petter77.financetracker.dto.request.CreateCategoryRequest;
import com.petter77.financetracker.dto.response.CategoryResponse;
import com.petter77.financetracker.service.CategoryService;

@RestController
@RequestMapping("/api/users/{userId}/categories")
public class CategoryController{

    private final CategoryService categoryService; 
    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@PathVariable Long userId, @RequestBody CreateCategoryRequest request) {
        CategoryResponse response = categoryService.createCategory(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
