package com.techframe.ecomerce.controller;


import com.techframe.ecomerce.model.Category;
import com.techframe.ecomerce.model.Product;
//import com.techframe.ecomerce.repository.ProductRepository;

import com.techframe.ecomerce.service.CategoryServiceM;
import com.techframe.ecomerce.service.ProductServiceM;
import com.techframe.ecomerce.service.SubcategoryServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FashionCategory {


    public SubcategoryServiceM subcategoryServiceM;


    @Autowired
    public void setSubcategoryService(SubcategoryServiceM subcategoryServiceM) {
        this.subcategoryServiceM = subcategoryServiceM;
    }

    public CategoryServiceM categoryServiceM;
    @Autowired
    public void setCategoryServiceM(CategoryServiceM categoryServiceM){
        this.categoryServiceM=categoryServiceM;
    }
    @Autowired
    public ProductServiceM productServiceM;

    @GetMapping("category/fashion")
    public String fashionCategory(Model model, Model model2){
        model.addAttribute("subcategories", subcategoryServiceM.listAllSubcategories());
        model2.addAttribute("category", categoryServiceM.listAllCategories());
        return "fashioncategory";

    }
    @GetMapping("category/fashion/jeans")
    public String fashionjeansCategory(Model model, Model model2){
        model.addAttribute("subcategories", subcategoryServiceM.listAllSubcategories());
        model2.addAttribute("product", productServiceM.listAllProducts());
        return "fashionjeans";
    }
    @GetMapping("category/fashion/shirt")
    public String fashionshirtCategory(Model model, Model model2){
        model.addAttribute("subcategories", subcategoryServiceM.listAllSubcategories());
        model2.addAttribute("products", productServiceM.listAllProducts());
        return "fashionshirt";
    }


}
