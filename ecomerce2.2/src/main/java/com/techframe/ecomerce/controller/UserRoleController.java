package com.techframe.ecomerce.controller;

import com.techframe.ecomerce.model.User;
import com.techframe.ecomerce.service.RoleServiceM;
import com.techframe.ecomerce.service.UserServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserRoleController {

    public UserServiceM usersServiceM;
    @Autowired
    public void setUsersServiceM(UserServiceM usersServiceM){
        this.usersServiceM=usersServiceM;
    }

    @Autowired
    public RoleServiceM roleServiceM;
    public void setRoleServiceM(RoleServiceM roleServiceM){
        this.roleServiceM=roleServiceM;
    }

    @GetMapping("/admin")
    public String showDashboard(){
        return "dashboardadmin";
    }

    @GetMapping("/admin/allusers")
    public String showUsers(Model model, Model model2, Model model3) {
        model.addAttribute("users", usersServiceM.listAllUsers());
        model2.addAttribute("roles", roleServiceM.listAllRoles());
        // model3.addAttribute("role", roleServiceM.)
        System.out.println("u");
        return "allUsers";
    }
    @GetMapping("/user/{id}")
    public String showUser(@PathVariable int id, Model model, Model model1) {

        model.addAttribute("user", usersServiceM.getUsersById(id));
        return "showUser";
    }

    @GetMapping("/admin/user/new")
    public String addNewUser(Model model, Model model1){

        model1.addAttribute("roles1" ,roleServiceM.listAllRoles());
        model.addAttribute("user",new User());
        return "addAccount";
    }

    @GetMapping("/admin/user/edit/{id}")
    public String editUser(@PathVariable int id , Model model, Model model1){
        model1.addAttribute("roles1" ,roleServiceM.listAllRoles());
        model.addAttribute("user",usersServiceM.getUsersById(id));
        return "addAccount";
    }

    @PostMapping("/admin/adduser")
    public String saveUser(User u){

        usersServiceM.saveUsers(u);
        return "redirect:/user/" + u.getId();
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

}
