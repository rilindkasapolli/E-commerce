package com.techframe.ecomerce.controller;


import com.techframe.ecomerce.model.Category;
import com.techframe.ecomerce.model.Product;
//import com.techframe.ecomerce.repository.ProductRepository;

import com.techframe.ecomerce.service.CategoryServiceM;
import com.techframe.ecomerce.service.ProductServiceM;
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

    public ProductServiceM productServiceM;

    @Autowired
    public void setProductService(ProductServiceM productServiceM) {
        this.productServiceM = productServiceM;
    }

    public CategoryServiceM categoryServiceM;
    @Autowired
    public void setCategoryServiceM(CategoryServiceM categoryServiceM){
        this.categoryServiceM=categoryServiceM;
    }


    @GetMapping("category/fashion")
    public String fashionCategory(Model model, Model model2){
        model.addAttribute("product", productServiceM.listAllProducts());
        model.addAttribute("category", categoryServiceM.listAllCategories());
        return "fashioncategory";

    }


}
