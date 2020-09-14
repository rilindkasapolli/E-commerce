package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Category;
import com.techframe.ecomerce.model.Product;
import com.techframe.ecomerce.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceM implements CategoryService{

    private CategoryRepository productRepository;

    @Autowired
    public void setProductRepository(CategoryRepository productRepository) {

        this.productRepository = productRepository;
    }


    @Override
    public Iterable<Category> listAllCategories() {

        return productRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {

        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Category getCategoryByName(String name) {
        return productRepository.findCategoryByName(name);
    }

    @Override
    public Category saveCategory(Category product) {

        return productRepository.save(product);
    }

    public void deleteCategory(Integer id) {
        productRepository.deleteById(id);
    }
}
