package com.techframe.ecomerce.controller;


import com.techframe.ecomerce.service.CategoryServiceM;
import com.techframe.ecomerce.service.ProductServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PhotoController implements ServletContextAware {
    @Autowired
    private ProductServiceM productServiceM;
    private ServletContext servletContext;
    @Autowired
    private CategoryServiceM categoryServiceM;

    @GetMapping("/services/photo/product/{id}")
    public String photoOfProduct(Model model, Model model2, @PathVariable("id")int id){
        model2.addAttribute("categories", categoryServiceM.listAllCategories());
        model.addAttribute("products", productServiceM.listAllProducts());
        return "allProducts";

    }
    private String uploadFile(MultipartFile multipartFile){
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(servletContext.getRealPath("/uploads/images/" + multipartFile.getOriginalFilename()));
            Files.write(path,bytes);
            return multipartFile.getOriginalFilename();
        }
        catch (Exception e){
            return null;
        }
    }



    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
}
