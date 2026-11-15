package com.techstore.tech_store.service;

import com.techstore.tech_store.dto.request.CategoryRequest;
import com.techstore.tech_store.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    Category createCategory(CategoryRequest request);
    List<Category> getAllCategories();
    List<Category> getActiveCategories();
    Optional<Category> getCategoryById(UUID id);
    Optional<Category> getCategoryBySlug(String slug);
    Category updateCategory(UUID id, CategoryRequest request);
    void deleteCategory(UUID id);
}
