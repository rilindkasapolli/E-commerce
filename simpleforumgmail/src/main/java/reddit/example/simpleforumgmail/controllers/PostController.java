package reddit.example.simpleforumgmail.controllers;


import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reddit.example.simpleforumgmail.models.*;
import reddit.example.simpleforumgmail.repository.CommentLikesRepository;
import reddit.example.simpleforumgmail.repository.LikesRepository;
import reddit.example.simpleforumgmail.repository.PostRepository;
import reddit.example.simpleforumgmail.services.*;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.LocalDateTime;

@Controller
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger("PostController.class");

    @Value("${admin.email}")
    private String adminEmail;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public LikesServiceM likesServiceM;
    @Autowired
    public CommentLikesServiceM commentlikesServiceM;
    @Autowired
    public LikesRepository likesRepository;
    @Autowired
    public RoleServiceM roleServiceM;
    @Autowired
    public CommentLikesRepository commentlikesRepository;
    @Autowired
    public PostServiceM postServiceM;
    @Autowired
    public PostRepository postRepository;
    @Autowired
    public SubredditServiceM subredditServiceM;
    @Autowired
    public CommentServiceM commentServiceM;
    @Autowired
    public UserServiceM userServiceM;

    @GetMapping("/subreddit/{id}/newPost")
    public String newPost(Model model, @PathVariable int id) {
        model.addAttribute("subreddit", subredditServiceM.getSubredditById(id));
        model.addAttribute("post", new Post());

        return "addPost";
    }

    @PostMapping("/newpost")
    public String savePost(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, Authentication authentication, @RequestParam("subreddit") Subreddit subreddit, @RequestParam("description") String description) {
        try {
            logger.info("Name= " + name);
            byte[] image = file.getBytes();
            Tika tika = new Tika();
            String mimeType = tika.detect(image);
            User u = userServiceM.findByEmailIgnoreCase(authentication.getName());

            Post model = new Post();
            model.setEmail(u.getEmail());
            model.setName(name);
            model.setImage(image);
            model.setSubreddit(subreddit.getIdsubreddit());
            model.setUser(u.getId());
            model.setDescription(description);
            u.getPosts().add(model);

            LocalDateTime dateNow = LocalDateTime.now(Clock.systemUTC());
            model.setTimeCreated(dateNow);

            if (mimeType.equals("image/png") || image.length == 0 || mimeType.equals("image/jpeg") || mimeType.equals("example/example") || mimeType.equals(",") || mimeType.equals("video/webm") || mimeType.equals("image/gif")) {
                postServiceM.savePost(model);
                return "redirect:/subreddit/post/" + model.getPostid();
            } else {
                return "error";
            }
        } catch (Exception e) {
            logger.error("ERROR", e);
            return "error";
        }
    }
    @PostMapping("newsettingspassword")
    public String newsettingspassword(@RequestParam("password1") String password1,@RequestParam("password2") String password2  , @RequestParam("oldpassword") String oldpassword, Authentication authentication)  {

        User u = userServiceM.findByEmailIgnoreCase(authentication.getName());

        if(bCryptPasswordEncoder.matches(oldpassword,u.getPassword()) && password1.equals(password2)){
            String s = bCryptPasswordEncoder.encode(password1);
            u.setPassword(s);
             userServiceM.saveImage(u);
            return "redirect:/profile/" + u.getEmail() ;
        }else {
            return "error";
        }

    }
    @PostMapping("newsettings")
    public String newsettings(@RequestParam("name") String name, @RequestParam ("about")String about,  Authentication authentication, @RequestParam("file") MultipartFile file) throws IOException {

        User u = userServiceM.findByEmailIgnoreCase(authentication.getName());

        byte[] image = file.getBytes();
        Tika tika = new Tika();
        String mimeType = tika.detect(image);
        if(!(image.length == 0)) {
            u.setProfilepicture(image);
        }
        u.setName(name);
        u.setAbout(about);


         userServiceM.saveImage(u);
        if (mimeType.equals("image/png")   || mimeType.equals("image/jpeg") ||image.length == 0 || mimeType.equals("example/example") || mimeType.equals(",") || mimeType.equals("video/webm") || mimeType.equals("image/gif")) {
            return "redirect:/profile/" + u.getEmail() + "/settings";
        } else {
            return "error";
        }
    }

    @GetMapping("/subreddit/post/{id}")
    public String getPostview(Model model, @PathVariable int id)  {
        model.addAttribute("post", postServiceM.getPostById(id));
        model.addAttribute("subreddits", subredditServiceM.listAllSubreddits());
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", commentServiceM.listAllComments());

        model.addAttribute("adminemail",adminEmail);
        Post post = postServiceM.getPostById(id);

        LocalDateTime dateNow = LocalDateTime.now(Clock.systemUTC());
        model.addAttribute("dateNow", dateNow);

        model.addAttribute("userid", userServiceM);


        model.addAttribute("name", post.getName());
        int k = post.getKarma();
        model.addAttribute("likes", k);
        byte[] encode = java.util.Base64.getEncoder().encode(post.getImage());
        model.addAttribute("imagee", new String(encode, StandardCharsets.UTF_8));

        return "viewPost";
    }


    @GetMapping("/subreddit/post/upvote/{id}")
    public String upKarma(@PathVariable("id") int id,Authentication authentication) {
        User u = userServiceM.findByEmailIgnoreCase(authentication.getName());
        Post pp = postServiceM.getPostById(id);
        Likes ll = likesServiceM.getLikesByUserIdAndPostId(u.getId(),pp.getPostid());
        int k = pp.getKarma();
        if (ll == null) {
            ll = new Likes();
            ll.setPostLike(pp.getPostid());
            ll.setUser(u.getId());
            pp.setKarma(++k);
            postServiceM.savePost(pp);
            likesServiceM.saveLikes(ll);
        } else {
            pp.setKarma(--k);
            postServiceM.savePost(pp);
            likesRepository.delete(ll);

            return "redirect:/subreddit/post/" + pp.getPostid();
        }
        return "redirect:/subreddit/post/" + pp.getPostid();
    }



    @GetMapping("/subreddit/post/delete/{id}")
    public String deleteP(@PathVariable("id") int id) {
        int x = postServiceM.getPostById(id).getSubreddit();
        postServiceM.deletePost(id);
        return "redirect:/subreddit/" + x;

    }

    @GetMapping("/subreddit/comment/upvote/{id}")
    public String upCommentkarma(@PathVariable("id") int id,Authentication authentication) {
        User u = userServiceM.findByEmailIgnoreCase(authentication.getName());
        Comment c = commentServiceM.getCommentById(id);
        CommentLikes ll = commentlikesServiceM.getCommentLikesByUserIdAndCommentId(u.getId(),c.getCommentid());
        int k = c.getCommentkarma();
        likesRepository.findAllByPost(id).size();
        if (ll == null) {
            ll = new CommentLikes();
            ll.setComment(c.getCommentid());
            ll.setUser(u.getId());
            c.setCommentkarma(++k);
            commentServiceM.saveComment(c);
            commentlikesServiceM.saveCommentLikes(ll);

        } else {
            c.setCommentkarma(--k);
            commentServiceM.saveComment(c);
            commentlikesRepository.delete(ll);

            return "redirect:/subreddit/post/" + c.getPost();
        }


        return "redirect:/subreddit/post/" + c.getPost();
    }

}
