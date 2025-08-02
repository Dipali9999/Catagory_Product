package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Category;
import com.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // ✅ Get all categories with pagination
    @GetMapping
    public List<Category> getAll(@RequestParam(defaultValue = "0") int page) {
        return categoryService.getAll(page);
    }

    // ✅ Get category by ID
    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    // ✅ Create a new category
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category saved = categoryService.create(category);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ Update a category by ID
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    // ✅ Delete a category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
