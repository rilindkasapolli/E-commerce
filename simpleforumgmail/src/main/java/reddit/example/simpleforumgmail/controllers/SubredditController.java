package reddit.example.simpleforumgmail.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reddit.example.simpleforumgmail.services.SubredditServiceM;

@Controller
public class SubredditController {

    @Autowired
    public SubredditServiceM subredditServiceM;


    @GetMapping("/login")
    public String login() {

        return "newLogin";
    }


}
