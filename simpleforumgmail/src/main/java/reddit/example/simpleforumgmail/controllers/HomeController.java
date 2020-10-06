package reddit.example.simpleforumgmail.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import reddit.example.simpleforumgmail.models.Post;

import reddit.example.simpleforumgmail.repository.PostRepository;
import reddit.example.simpleforumgmail.services.CommentServiceM;
import reddit.example.simpleforumgmail.services.PostServiceM;
import reddit.example.simpleforumgmail.services.SubredditServiceM;
import reddit.example.simpleforumgmail.services.UserServiceM;


import java.util.*;
import java.util.stream.StreamSupport;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger("HomeController.class");


    @Autowired
    public void setPostServiceM(PostServiceM postServiceM) {
        this.postServiceM = postServiceM;
    }

    @Autowired
    public SubredditServiceM subredditServiceM;

    @Autowired
    public CommentServiceM commentServiceM;
    @Autowired
    public PostServiceM postServiceM;
    @Autowired
    public PostRepository postRepository;
    @Autowired
    public UserServiceM userServiceM;


    @GetMapping("/login")
    public String login() {


        return "newLogin";
    }


    @GetMapping("/")
    public String index(Model model, Model model2, Model model3, Model model4, @Param("keyword") String keyword) {

        Iterable<Post> post = postServiceM.listAllPosts();

        model.addAttribute("posts", postServiceM.listAllPosts());
        model2.addAttribute("subreddits", subredditServiceM.listAllSubreddits());
        model3.addAttribute("subredditnumber", StreamSupport.stream(post.spliterator(), false).count());
        model4.addAttribute("topPosts", postRepository.findTop5ByOrderByKarmaDesc());


        return "index";
    }

    @GetMapping("/a")
    public String a(@Param("keyword") String keyword, Model model) {
        List<Post> listProducts = postServiceM.listAllSearch(keyword);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("keyword", keyword);
        model.addAttribute("subreddits", subredditServiceM.listAllSubreddits());
        return "searchedposts";
    }


}
