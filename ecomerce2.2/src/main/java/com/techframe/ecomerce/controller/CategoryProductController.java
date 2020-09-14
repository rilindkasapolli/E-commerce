package com.techframe.ecomerce.controller;

import com.techframe.ecomerce.model.Category;
import com.techframe.ecomerce.model.Product;
//import com.techframe.ecomerce.repository.ProductRepository;

import com.techframe.ecomerce.model.Subcategory;
import com.techframe.ecomerce.repository.SubcategoryRepository;
import com.techframe.ecomerce.service.CategoryServiceM;
import com.techframe.ecomerce.service.ProductServiceM;
import com.techframe.ecomerce.service.SubcategoryService;
import com.techframe.ecomerce.service.SubcategoryServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    public SubcategoryServiceM subcategoryServiceM;
    @Autowired
    public void setSubcategoryServiceM(SubcategoryServiceM subcategoryServiceM){
        this.subcategoryServiceM=subcategoryServiceM;
    }
    @Autowired
    public SubcategoryRepository subcategoryRepository;

    //show all products and a product by id

    @PostMapping("/subcategory")
    public String saveSubcategory(Subcategory s){


        subcategoryServiceM.saveSubcategory(s);




        return "redirect:/category/subcategory/" +s.getSubcategoryid();
    }

    @GetMapping("/category/addsubcategory")
    public String newSubCategory  (Model model, Model model2,Model model3){
        model.addAttribute("subcategory", new Subcategory());
        model2.addAttribute("categoryy", categoryServiceM.listAllCategories());
        return "addSubcategory";
    }
    @GetMapping("/category/subcategory/edit/{id}")
    public String editSubcategory(@PathVariable("id") int id, Model model, Model model2) {
        model2.addAttribute("categoryy", categoryServiceM.listAllCategories());
        model.addAttribute("subcategory", subcategoryServiceM.getSubcategoryById(id));
        return "addSubcategory";

    }
    @GetMapping("/category/subcategories")
    public String showSubcategories( Model model, Model model2){
        model2.addAttribute("categories", categoryServiceM.listAllCategories());
        model.addAttribute("subcategories", subcategoryServiceM.listAllSubcategories());
        return "allSubcategories";
    }
    @GetMapping("/category/subcategory/{id}")
    public String showSubcategory(@PathVariable("id") int id,Model model, Model model2){
        model.addAttribute("subcategory", subcategoryServiceM.getSubcategoryById(id));
        model2.addAttribute("categories",categoryServiceM.listAllCategories());

        return "showSubcategory";
    }

    @DeleteMapping("deletesubcategory")
    public void deleteSubcategory(int id) {

        subcategoryServiceM.deleteSubcategory(id);

    }


    @GetMapping("/category/subcategory/delete/{id}")
    public String deleteSC(@PathVariable("id") int id,Model model, RedirectAttributes redirectAttributes) {

        subcategoryServiceM.deleteSubcategory(id);
        redirectAttributes.addFlashAttribute("x", "Post was deleted!");
        return "redirect:/category/subcategories";
    }

    @GetMapping("/services")
    public String showServices(Model model, Model model2, Model model3) {
        model3.addAttribute("subcategories", subcategoryServiceM.listAllSubcategories());
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
    @GetMapping("/category/show/{id}")
    public String categoryShowByName(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("category", categoryServiceM.getCategoryById(id));
        return "categoryType";
    }
    @GetMapping("/category/subcategory/show/{subname}")
    public String subcategoryShowByName(@PathVariable(value = "subname") String subname,  Model model) {
        model.addAttribute("subcategory", subcategoryServiceM.getSubcategoryByName(subname));

        return "subcategoryType";
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



        for ( Subcategory sa : categoryServiceM.getCategoryById(id).getSubcategories()

             ) {
                subcategoryRepository.delete(sa);
        }
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
        model1.addAttribute("subcategories" ,subcategoryServiceM.listAllSubcategories());
        model.addAttribute("product", productServiceM.getProductById(id));
        return "addProduct";

    }
    //test part

    @GetMapping("/services/new")
    public String newProduct(Model model, Model model1, Model model2) {
        model2.addAttribute("subcategories", subcategoryServiceM.listAllSubcategories());
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


