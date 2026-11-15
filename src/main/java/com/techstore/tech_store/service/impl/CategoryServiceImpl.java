package com.techstore.tech_store.service.impl;

import com.techstore.tech_store.dto.request.CategoryRequest;
import com.techstore.tech_store.entity.Category;
import com.techstore.tech_store.entity.CategoryStatus;
import com.techstore.tech_store.repository.CategoryRepository;
import com.techstore.tech_store.service.CategoryService;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CategoryRequest request) {
        if (categoryRepository.findBySlug(request.getSlug()).isPresent()) {
            throw  new IllegalArgumentException("Category already exists" + request.getSlug());
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setSlug(request.getSlug().toLowerCase().trim());
        category.setDescription(request.getDescription());
        category.setStatus(request.getStatus() != null ? request.getStatus() : CategoryStatus.ACTIVE);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getActiveCategories() {
        return categoryRepository.findByStatus(CategoryStatus.ACTIVE);
    }

    @Override
    public Optional<Category> getCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug);
    }

    @Override
    public Category updateCategory(UUID id, CategoryRequest request){
        Category category = categoryRepository.findById(id).orElseThrow(()
                -> new IllegalIdentifierException("Category not found with id" + id));

        if(!category.getSlug().equalsIgnoreCase(request.getSlug())
                && categoryRepository.findBySlug(request.getSlug()).isPresent()){
            throw  new IllegalArgumentException("Category already exists" + request.getSlug());
        }

        category.setName(request.getName());
        category.setSlug(request.getSlug().toLowerCase().trim());
        category.setDescription(request.getDescription());
        if(request.getStatus() != null){
            category.setStatus(request.getStatus());
        }

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        if(!categoryRepository.existsById(id)){
            throw  new IllegalArgumentException("Category not found with id" + id);
        }
        categoryRepository.deleteById(id);
    }
}
