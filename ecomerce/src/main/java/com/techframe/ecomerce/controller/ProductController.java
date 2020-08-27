package com.techframe.ecomerce.controller;

import com.techframe.ecomerce.model.Product;
//import com.techframe.ecomerce.repository.ProductRepository;
import com.techframe.ecomerce.model.Users;
import com.techframe.ecomerce.repository.ProductRepository;
import com.techframe.ecomerce.service.ProductServiceM;
import com.techframe.ecomerce.service.UsersService;
import com.techframe.ecomerce.service.UsersServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {


    public ProductServiceM productServiceM;

    @Autowired
    public void setProductService(ProductServiceM productServiceM) {

        this.productServiceM = productServiceM;
    }
    public UsersServiceM usersServiceM;
    @Autowired
    public void setUsersServiceM(UsersServiceM usersServiceM){
        this.usersServiceM=usersServiceM;
    }

//random
    @GetMapping("/about")
    public String showAbout(Model model) {
        return "about";
    }
    //show all products and a product by id
    @GetMapping("/services")
    public String showServices(Model model) {
        model.addAttribute("products", productServiceM.listAllProducts());
        System.out.println("a");
        return "allProducts";
    }
    @GetMapping("/viewtesting/{id}")
    public String productShowTest(@PathVariable int id, Model model) {
        model.addAttribute("product", productServiceM.getProductById(id));
        return "viewtest";
    }
    //ADMIN
    @GetMapping("/admin")
    public String showDashboard(){
        return "dashboardadmin";
    }
    //show all users
    @GetMapping("/admin/allusers")
    public String showUsers(Model model) {
        model.addAttribute("users", usersServiceM.listAllProducts());
        System.out.println("u");
        return "allUsers";
    }
    //show user
    @GetMapping("/user/{id}")
    public String showUser(@PathVariable int id, Model model) {
        model.addAttribute("user", usersServiceM.getUsersById(id));
        return "showUser";
    }
    @GetMapping("/admin/user/new")
    public String addNewUser(Model model){
        model.addAttribute("user",new Users());
        return "addAccount";
    }
    @GetMapping("/admin/user/edit/{id}")
    public String editUser(@PathVariable int id , Model model){
        model.addAttribute("user",usersServiceM.getUsersById(id));
        return "addAccount";
    }
    //ADMIN END
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productServiceM.getProductById(id));
        return "addProduct";

    }

    @GetMapping("/services/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/service")
    public String saveProduct(Product p) {
        productServiceM.saveProduct(p);
        return "redirect:/viewtesting/" + p.getId();
    }
    @PostMapping("/admin/adduser")
    public String saveUser(Users u){
        usersServiceM.saveUsers(u);
        return "redirect:/user/" + u.getUser_id();
    }
    @DeleteMapping("deleteuser")
    public void deleteUser(int id) {

        usersServiceM.deleteProduct(id);

    }

    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes) {

        usersServiceM.deleteProduct(id);
        redirectAttributes.addFlashAttribute("x", "Post was deleted!");
        return "redirect:/admin/allusers";
    }
    @DeleteMapping("delete")
    public void deleteProduct(int id) {

        productServiceM.deleteProduct(id);

    }

    @GetMapping("/viewtesting/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {

        productServiceM.deleteProduct(id);
        redirectAttributes.addFlashAttribute("x", "Post was deleted!");
        return "redirect:/services";
    }

}


