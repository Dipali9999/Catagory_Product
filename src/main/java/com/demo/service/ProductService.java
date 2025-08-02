package com.demo.service;

import java.util.List;

import com.demo.dto.ProductDTO;
import com.demo.model.Product;

public interface ProductService {
    List<ProductDTO> getAll(int page);
    ProductDTO getById(Long id);
    ProductDTO create(Product product);
    ProductDTO update(Long id, Product product);
    void delete(Long id);
}
