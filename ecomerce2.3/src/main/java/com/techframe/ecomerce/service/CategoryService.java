package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Category;
import com.techframe.ecomerce.model.Product;
import com.techframe.ecomerce.model.User;

public interface CategoryService {

    Iterable<Category> listAllCategories();

    Category getCategoryById(Integer id);

    Category getCategoryByName(String name);

    Category saveCategory(Category category);
}
