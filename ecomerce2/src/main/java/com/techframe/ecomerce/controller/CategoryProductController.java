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
public class CategoryProductController {

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

    @GetMapping("/about")
    public String showAbout(Model model) {
        return "about";
    }
    //show all products and a product by id


    @GetMapping("/services")
    public String showServices(Model model, Model model2) {
        model2.addAttribute("categories", categoryServiceM.listAllCategories());
        model.addAttribute("products", productServiceM.listAllProducts());
        System.out.println("a");
        return "allProducts";
    }
    @GetMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryServiceM.listAllCategories());
        System.out.println("a");
        return "allCategories";
    }
    @GetMapping("/category/{id}")
    public String categoryShowById(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryServiceM.getCategoryById(id));
        return "showCategory";
    }

    @GetMapping("/viewtesting/{id}")
    public String productShowTest(@PathVariable int id, Model model) {
        model.addAttribute("product", productServiceM.getProductById(id));
        return "viewtest";
    }
    @GetMapping("/category/edit/{id}")
    public String editCategory(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryServiceM.getCategoryById(id));
        return "addCategory";

    }

    @GetMapping("/categories/new")
    public String newCategory(Model model, Model model2) {
        model2.addAttribute("products", productServiceM.listAllProducts());
        model.addAttribute("category", new Category());
        return "addCategory";
    }
    @PostMapping("/category")
    public String saveCategory(Category p) {
        categoryServiceM.saveCategory(p);
        return "redirect:/category/" + p.getCategoryid();
    }




    @DeleteMapping("deletecategory")
    public void deleteCategory(int id) {

        categoryServiceM.deleteCategory(id);

    }


    @GetMapping("/category/delete/{id}")
    public String deleteC(@PathVariable int id, RedirectAttributes redirectAttributes) {

        categoryServiceM.deleteCategory(id);
        redirectAttributes.addFlashAttribute("x", "Post was deleted!");
        return "redirect:/categories";
    }




    @GetMapping("/viewtesting/delasdeteasdfasasdfasdf/ABasdasS/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {

        productServiceM.deleteProduct(id);
        redirectAttributes.addFlashAttribute("x", "Post was deleted!");
        return "redirect:/services";
    }
    @DeleteMapping("delete")
    public void deleteProduct(int id) {

        productServiceM.deleteProduct(id);

    }


    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable int id, Model model, Model model1) {
        model1.addAttribute("categories" ,categoryServiceM.listAllCategories());
        model.addAttribute("product", productServiceM.getProductById(id));
        return "addProduct";

    }

    @GetMapping("/services/new")
    public String newProduct(Model model, Model model1) {
        model1.addAttribute("categories" ,categoryServiceM.listAllCategories());
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/service")
    public String saveProduct(Product p) {
        productServiceM.saveProduct(p);
        return "redirect:/viewtesting/" + p.getIdproduct();
    }








}


