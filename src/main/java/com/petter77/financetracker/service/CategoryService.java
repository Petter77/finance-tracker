package com.petter77.financetracker.service;
import com.petter77.financetracker.dto.response.CategoryResponse;
import com.petter77.financetracker.model.Category;
import com.petter77.financetracker.dto.request.CreateCategoryRequest;
import com.petter77.financetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.petter77.financetracker.model.User;
import com.petter77.financetracker.repository.UserRepository;

@Service
public class CategoryService{
    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository; 

    public CategoryService(
        CategoryRepository categoryRepository,
        UserRepository userRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CategoryResponse createCategory(Long userId, CreateCategoryRequest request) {

        if (categoryRepository.existsByUserIdAndName(userId, request.getName())) {
            throw new IllegalArgumentException("Category already exists"); 
        }

        Category category = new Category();
        category.setName(request.getName()); 
        
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        category.setUser(user);

        Category savedCategory = categoryRepository.save(category); 
        return new CategoryResponse(
            savedCategory.getId(),
            savedCategory.getName(),
            savedCategory.getUser().getId(),
            savedCategory.getCreatedAt(),
            savedCategory.getUpdatedAt()
        );
    } 
}
