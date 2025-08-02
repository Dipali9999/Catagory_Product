package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.dto.ProductDTO;
import com.demo.model.Product;
import com.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "0") int page) {
        List<ProductDTO> products = productService.getAll(page);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        ProductDTO created = productService.create(product);
        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        ProductDTO updated = productService.update(id, newProduct);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
