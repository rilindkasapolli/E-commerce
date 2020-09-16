package com.techframe.ecomerce.controller;


import com.techframe.ecomerce.model.Post;
import com.techframe.ecomerce.service.CategoryServiceM;
import com.techframe.ecomerce.service.PostServiceM;
import com.techframe.ecomerce.service.ProductServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PostController {

    @Autowired
    public ProductServiceM productServiceM;


    public PostServiceM postServiceM;

    @Autowired
    public void setPostServiceM(PostServiceM postServiceM) {
        this.postServiceM = postServiceM;
    }

    //@RequestMapping(value = "/username", method = RequestMethod.GET)
    //  @ResponseBody
    // public String currentUserName(Authentication authentication) {
    //     return authentication.getName();
    //  }

    @GetMapping("/viewtesting/{id}/newPost")

    public String newPost(Model model, Model model2, @PathVariable int id) {
        model.addAttribute("product", productServiceM.getProductById(id));
        model2.addAttribute("post", new Post());

        return "newpost";
    }

    @PostMapping("/newposts")
    public String savePost(Post p) {


        postServiceM.savePost(p);
        return "redirect:/viewtesting/" + p.getProduct();
    }

}
