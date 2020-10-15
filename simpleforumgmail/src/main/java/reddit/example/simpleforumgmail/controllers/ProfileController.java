package reddit.example.simpleforumgmail.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reddit.example.simpleforumgmail.models.Role;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.repository.CommentRepository;
import reddit.example.simpleforumgmail.repository.PostRepository;
import reddit.example.simpleforumgmail.services.*;


import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/profile/{email}")
public class ProfileController {

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

    @GetMapping()
    public String profile(@PathVariable String email, Model model) {
        User uu = userServiceM.findByEmailIgnoreCase(email);
        Role r = roleServiceM.getRoleById(uu.getRole());

        model.addAttribute("posts", postRepository.findByEmailContainingOrderByTimeCreatedDesc(email));
        LocalDateTime dateNow = LocalDateTime.now(Clock.systemUTC());
        model.addAttribute("dateNow", dateNow);
        model.addAttribute("user",uu);
        model.addAttribute("roles",r.getName());

        byte[] encode = java.util.Base64.getEncoder().encode(uu.getProfilepicture());
        model.addAttribute("image", new String(encode, StandardCharsets.UTF_8));
        return "userprofile";
    }
    @GetMapping("posts")
    public String profileposts(@PathVariable String email, Model model)  {
        User uu = userServiceM.findByEmailIgnoreCase(email);
        Role r = roleServiceM.getRoleById(uu.getRole());
        model.addAttribute("posts", postRepository.findByEmailContainingOrderByTimeCreatedDesc(email));
        LocalDateTime dateNow = LocalDateTime.now(Clock.systemUTC());
        model.addAttribute("dateNow", dateNow);
        model.addAttribute("user",uu);
        model.addAttribute("roles",r.getName());

        byte[] encode = java.util.Base64.getEncoder().encode(uu.getProfilepicture());
        model.addAttribute("image", new String(encode, StandardCharsets.UTF_8));
        return "userprofile";
    }
    @GetMapping("comments")
    public String profilecomments(@PathVariable String email, Model model)  {
        User uu = userServiceM.findByEmailIgnoreCase(email);
        Role r = roleServiceM.getRoleById(uu.getRole());

        LocalDateTime dateNow = LocalDateTime.now(Clock.systemUTC());
        model.addAttribute("dateNow", dateNow);
        model.addAttribute("user",uu);
        model.addAttribute("roles",r.getName());
        model.addAttribute("post", postServiceM);

        model.addAttribute("usercomment", commentRepository.findByUsernameContainingOrderByTimeCreatedDesc(uu.getName()));
        byte[] encode = java.util.Base64.getEncoder().encode(uu.getProfilepicture());
        model.addAttribute("imagee", new String(encode, StandardCharsets.UTF_8));
        return "profilecomments";
    }
    @GetMapping("settings")
    public String settings(@PathVariable String email, Model model, Authentication authentication) {
        User u = userServiceM.findByEmailIgnoreCase(email);
        if(!authentication.getName().equals(email)){
            return "error";
        }
        model.addAttribute("user", u);

        byte[] encode = java.util.Base64.getEncoder().encode(u.getProfilepicture());
        model.addAttribute("imagee", new String(encode, StandardCharsets.UTF_8));
        return "accountSettingsGeneral";

    }
    @GetMapping("settings/newpassword")
    public String settingspassword(@PathVariable String email, Model model, Authentication authentication)  {

        User u = userServiceM.findByEmailIgnoreCase(email);
        if(!authentication.getName().equals(email)){
            return "error";
        }
        model.addAttribute("user", u);

        byte[] encode = java.util.Base64.getEncoder().encode(u.getProfilepicture());
        model.addAttribute("imagee", new String(encode, StandardCharsets.UTF_8));
        return "accountSettingsPassword";

    }


}
