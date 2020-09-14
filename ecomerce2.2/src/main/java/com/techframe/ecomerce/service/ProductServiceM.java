package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Product;
import com.techframe.ecomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceM implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }


    @Override
    public Iterable<Product> listAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {

        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product getProductByProductName(String name) {
        return productRepository.findProductByProductName(name);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
