package com.techframe.ecomerce.repository;


import com.techframe.ecomerce.model.Subcategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SubcategoryRepository extends CrudRepository<Subcategory, Integer> {
    Subcategory findSubcategoryByName(String name);
    List<Subcategory> findAll();


}
