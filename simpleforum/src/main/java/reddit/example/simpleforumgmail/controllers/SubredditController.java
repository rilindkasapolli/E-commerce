package reddit.example.simpleforumgmail.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reddit.example.simpleforumgmail.models.Subreddit;
import reddit.example.simpleforumgmail.services.PostServiceM;
import reddit.example.simpleforumgmail.services.SubredditServiceM;
import reddit.example.simpleforumgmail.services.UserServiceM;

@Controller
public class SubredditController {

    @Autowired
    public SubredditServiceM subredditServiceM;

    @Autowired
    public UserServiceM userServiceM;
    @Autowired
    public PostServiceM postServiceM;

    @GetMapping("/subreddit/new")
    public String newSubreddit(Model model) {
        model.addAttribute("subreddit", new Subreddit());
        return "addSubreddit";
    }

    @PostMapping("/newsubreddit")
    public String saveSubreddit(Subreddit p) {
        subredditServiceM.saveSubreddit(p);
        return "redirect:/subreddit/" + p.getIdsubreddit();
    }

    @GetMapping("/subreddit/{id}")
    public String viewSubreddit(Model model, @PathVariable int id, Model model2, Model model3) {
        model3.addAttribute("subreddits", subredditServiceM.listAllSubreddits());
        model.addAttribute("subreddit", subredditServiceM.getSubredditById(id));
        model2.addAttribute("posts", postServiceM.listAllPosts());
        return "viewSubreddit";
    }

    @GetMapping("/subreddit/delete/{id}")
    public String deleteS(@PathVariable("id") int id) {
        subredditServiceM.deleteSubreddit(id);
        return "redirect:/";

    }

}
