package reddit.example.simpleforumgmail.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reddit.example.simpleforumgmail.models.Post;
import reddit.example.simpleforumgmail.repository.CommentRepository;
import reddit.example.simpleforumgmail.repository.PostRepository;
import reddit.example.simpleforumgmail.services.*;
import java.util.*;


@Controller
public class HomeController {

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
    @Autowired
    public RoleServiceM roleServiceM;
    @Autowired
    public CommentRepository commentRepository;

    @GetMapping("/login")
    public String login() {

        return "newLogin";
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postServiceM.listAllPosts());
        model.addAttribute("subreddits", subredditServiceM.listAllSubreddits());
        model.addAttribute("topPosts", postRepository.findTop5ByOrderByKarmaDesc());
        model.addAttribute("user", userServiceM);

        return "index";
    }
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("users", userServiceM.listAllUsers());

        return "adminDashboard";
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
