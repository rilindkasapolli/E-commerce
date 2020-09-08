package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Category;
import com.techframe.ecomerce.model.Product;

public interface CategoryService {

    Iterable<Category> listAllCategories();

    Category getCategoryById(Integer id);

    Category saveCategory(Category category);
}
