package com.demo.service;

import java.util.List;

import com.demo.model.Category;

public interface CategoryService {
    List<Category> getAll(int page);
    Category getById(Long id);
    Category create(Category category);
    Category update(Long id, Category category);
    void delete(Long id);
}

