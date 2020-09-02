package com.techframe.ecomerce.repository;

import com.techframe.ecomerce.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Integer> {
}
