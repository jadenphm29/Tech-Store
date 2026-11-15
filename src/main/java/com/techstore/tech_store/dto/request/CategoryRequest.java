package com.techstore.tech_store.dto.request;


import com.techstore.tech_store.entity.CategoryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequest {

    @NotBlank(message = "Category name cannot be empty")
    @Size(max = 100, message = "Category name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Slug cannot be empty")
    @Size(max = 100, message = "Slug must be not exceed 100 characters")
    private String slug;

    private String description;

    private CategoryStatus status = CategoryStatus.ACTIVE;

    public CategoryRequest() {
    }

    public CategoryRequest(String name, String slug, String description, CategoryStatus status) {
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryStatus getStatus() {
        return status;
    }

    public void setStatus(CategoryStatus status) {
        this.status = status;
    }
}
