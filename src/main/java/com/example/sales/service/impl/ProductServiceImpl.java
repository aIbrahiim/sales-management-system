package com.example.sales.service.impl;

import com.example.sales.exception.InvalidProductException;
import com.example.sales.exception.ProductNotFoundException;
import com.example.sales.model.Product;
import com.example.sales.repository.ProductRepository;
import com.example.sales.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        log.info("Fetching product with ID: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with id: {}", id);
                    throw new ProductNotFoundException("Product not found with id: " + id);
                });
    }

    @Override
    public Product createProduct(Product product) {
        validateProduct(product);
        log.info("Creating product: {}", product.getName());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        log.info("Updating product with ID: {}", id);
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setCategory(productDetails.getCategory());
        product.setAvailableQuantity(productDetails.getAvailableQuantity());
        product.setPrice(productDetails.getPrice());
        validateProduct(product);
        Product updatedProduct = productRepository.save(product);
        log.info("Product updated successfully with ID: {}", id);
        return updatedProduct;
    }

    @Override
    public Product deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error: No product found with ID: {}", id);
                    return new ProductNotFoundException("No product found with ID: " + id);
                });
        productRepository.deleteById(id);
        log.info("Product deleted successfully with ID: {}", id);
        return Product.builder()
                .id(id)
                .build();
    }

    private static void validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            log.error("Attempted to create a product with an empty name");
            throw new InvalidProductException("Product name cannot be empty");
        }
        if (product.getPrice() <= 0) {
            log.error("Attempted to create a product with a negative price: {}", product.getPrice());
            throw new InvalidProductException("Product price must be positive");
        }
    }

}
