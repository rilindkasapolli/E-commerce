package com.techframe.ecomerce.controller;

import com.techframe.ecomerce.service.ProductServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    public ProductServiceM productServiceM;

    @Autowired
    public void setProductServiceM(ProductServiceM productServiceM) {
        this.productServiceM = productServiceM;
    }



    @GetMapping("/")
    public String getHome2(Model model) {
        model.addAttribute("products", productServiceM.listAllProducts());
        return "index";
    }


}
