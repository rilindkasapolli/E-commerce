package reddit.example.simpleforumgmail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reddit.example.simpleforumgmail.models.Comment;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.services.CommentServiceM;
import reddit.example.simpleforumgmail.services.PostServiceM;
import reddit.example.simpleforumgmail.services.UserServiceM;
import java.time.Clock;
import java.time.LocalDateTime;

@Controller
public class CommentController {

    @Autowired
    public PostServiceM postServiceM;

    @Autowired
    public UserServiceM userServiceM;

    @Autowired
    public CommentServiceM commentServiceM;


    @PostMapping("/newcomment")
    public String saveComment(Comment p, Authentication authentication) {

        User u = userServiceM.findByEmailIgnoreCase(authentication.getName());

        p.setUser(u.getId());
        p.setUsername(u.getName());
        p.setEmail(u.getEmail());
        p.setCommentkarma(0);
        LocalDateTime dateNow = LocalDateTime.now(Clock.systemUTC());
        p.setTimeCreated(dateNow);
        commentServiceM.saveComment(p);
        return "redirect:/subreddit/post/" + p.getPost();
    }


    @GetMapping("/subreddit/post/comment/delete/{id}")
    public String deleteC(@PathVariable("id") int id) {
        int x = commentServiceM.getCommentById(id).getPost();
        commentServiceM.deleteComment(id);
        return "redirect:/subreddit/post/" + x;

    }
}
