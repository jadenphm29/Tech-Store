package com.techstore.tech_store.dto.response;

import com.techstore.tech_store.entity.Category;
import com.techstore.tech_store.entity.CategoryStatus;

import java.util.UUID;

public class CategoryResponse {
    private UUID id;
    private String name;
    private String description;
    private CategoryStatus status;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.status = category.getStatus();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CategoryStatus getStatus() {
        return status;
    }
}
