package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.CategoryDao;
import com.demo.model.Category;

@Service
@Transactional 
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getAll(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return categoryDao.findAll(pageable).getContent();
    }

    @Override
    public Category getById(Long id) {
        return categoryDao.findById(id).orElse(null);
    }

    @Override
    public Category create(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public Category update(Long id, Category newCategory) {
        Category existing = categoryDao.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(newCategory.getName());
            return categoryDao.save(existing); // No need for .update()
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        categoryDao.deleteById(id);
    }
}
