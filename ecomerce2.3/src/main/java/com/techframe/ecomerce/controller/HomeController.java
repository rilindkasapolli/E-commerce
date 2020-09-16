package com.techframe.ecomerce.controller;

import com.techframe.ecomerce.service.CategoryServiceM;
import com.techframe.ecomerce.service.ProductServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    public ProductServiceM productServiceM;
    @Autowired
    public CategoryServiceM categoryServiceM;

    @Autowired
    public void setProductServiceM(ProductServiceM productServiceM) {
        this.productServiceM = productServiceM;
    }


    @GetMapping("/")
    public String getHome2(Model model, Model model2) {
        model.addAttribute("products", productServiceM.listAllProducts());
        model2.addAttribute("categories", categoryServiceM.listAllCategories());
        return "index";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }


}
