package com.techframe.ecomerce.repository;

import com.techframe.ecomerce.model.Category;
import com.techframe.ecomerce.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Integer> {
    Category findCategoryByName(String name);

}
