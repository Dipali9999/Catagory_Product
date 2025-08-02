package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.CategoryDao;
import com.demo.dao.ProductDao;
import com.demo.dto.ProductDTO;
import com.demo.model.Category;
import com.demo.model.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<ProductDTO> getAll(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> productPage = productDao.findAll(pageable);

        return productPage.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = productDao.findById(id).orElse(null);
        return product != null ? convertToDTO(product) : null;
    }

    @Override
    public ProductDTO create(Product product) {
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            return null;
        }

        Long categoryId = product.getCategory().getId();
        Category category = categoryDao.findById(categoryId).orElse(null);
        if (category != null) {
            product.setCategory(category);
            Product savedProduct = productDao.save(product);
            return convertToDTO(savedProduct);
        }
        return null;
    }

    @Override
    public ProductDTO update(Long id, Product newProduct) {
        Product existingProduct = productDao.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(newProduct.getName());
            existingProduct.setPrice(newProduct.getPrice());

            if (newProduct.getCategory() != null && newProduct.getCategory().getId() != null) {
                Long categoryId = newProduct.getCategory().getId();
                Category category = categoryDao.findById(categoryId).orElse(null);
                if (category != null) {
                    existingProduct.setCategory(category);
                }
            }

            Product updatedProduct = productDao.save(existingProduct);
            return convertToDTO(updatedProduct);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        productDao.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategoryName(product.getCategory() != null ? product.getCategory().getName() : null);
        return dto;
    }
}
