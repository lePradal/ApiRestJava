package com.apirest.products.controller;

import com.apirest.products.models.Product;
import com.apirest.products.repository.ProductRepository;
import org.assertj.core.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class ProductController {
    Logger LOGGER = LoggerFactory.getLogger("ProductControllerLogger");

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> productList() {

        try {
            LOGGER.info("Returning the list of products.");
            return productRepository.findAll();
        } catch (NoResultException e) {
            LOGGER.error("It was not possible to return the product list.");
            throw new NoResultException(e.getMessage());
        }
    }

    @GetMapping("/product/{id}")
    public Product product(@PathVariable(value = "id") long id) {
        checkIfTheProductExists(id);

        try {
            LOGGER.info("Returning the product.");
            return productRepository.findById(id);
        } catch (NoResultException e) {
            LOGGER.error("It was not possible to return the product.");
            throw new NoResultException(e.getMessage());
        }
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product) {

        try {
            LOGGER.info("Creating the product.");
            return productRepository.save(product);
        } catch (IllegalArgumentException e) {
            LOGGER.error("It was not possible to create the product.");
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @DeleteMapping("/product")
    public void deleteProduct(@RequestBody Product product) {
        Product existingProduct = checkIfTheProductExists(product.getId());

        if(isEquals(product, existingProduct)) {
            throw new IllegalArgumentException("Invalid product.");
        }

        try {
            LOGGER.info("Deleting the product.");
            productRepository.delete(product);
        } catch (IllegalArgumentException e ) {
            LOGGER.error("It was not possible to delete the product.");
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {

        try {
            LOGGER.info("Updating the product.");
            return productRepository.save(product);
        } catch (IllegalArgumentException e) {
            LOGGER.error("It was not possible to update the product.");
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private Product checkIfTheProductExists(long id) {
        Product existingProduct = productRepository.findById(id);
        Preconditions.checkArgument(existingProduct != null, "Product not exists.");
        return existingProduct;
    }

    private boolean isEquals(Product product, Product existingProduct) {
        return !existingProduct.getName().equals(product.getName()) ||
                existingProduct.getAmount() != product.getAmount() ||
                !existingProduct.getValue().equals(product.getValue());
    }
}
