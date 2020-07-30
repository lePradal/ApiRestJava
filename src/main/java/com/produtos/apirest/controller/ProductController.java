package com.produtos.apirest.controller;

import com.produtos.apirest.models.Product;
import com.produtos.apirest.repository.ProductRepository;
import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> productList() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product product(@PathVariable(value = "id") long id) {
        return productRepository.findById(id);
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("/product")
    public void deleteProduct(@RequestBody Product product) {
        Product existingProduct = productRepository.findById(product.getId());

        Preconditions.checkArgument(existingProduct != null, "Product not exists.");

        if(isEquals(product, existingProduct)) {
            throw new RuntimeException("Invalid product.");
        }

        productRepository.delete(product);
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    private boolean isEquals(Product product, Product existingProduct) {
        return !existingProduct.getName().equals(product.getName()) ||
                existingProduct.getAmount() != product.getAmount() ||
                !existingProduct.getValue().equals(product.getValue());
    }
}
