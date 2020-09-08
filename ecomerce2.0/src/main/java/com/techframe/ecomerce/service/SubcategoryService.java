package com.techframe.ecomerce.service;


import com.techframe.ecomerce.model.Category;
import com.techframe.ecomerce.model.Subcategory;


import java.util.List;

public interface SubcategoryService  {
    List<Subcategory> listAllSubcategories();
    Subcategory saveSubcategory(Subcategory subcategory);
    Subcategory getSubcategoryById(Integer id);
    Subcategory getSubcategoryByName(String username);
    void deleteSubcategoryIfNoCategory();

}
