package reddit.example.simpleforumgmail.controllers;


import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import reddit.example.simpleforumgmail.models.Comment;
import reddit.example.simpleforumgmail.models.Post;
import reddit.example.simpleforumgmail.models.Subreddit;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.repository.PostRepository;
import reddit.example.simpleforumgmail.services.CommentServiceM;
import reddit.example.simpleforumgmail.services.PostServiceM;
import reddit.example.simpleforumgmail.services.SubredditServiceM;
import reddit.example.simpleforumgmail.services.UserServiceM;

import java.io.UnsupportedEncodingException;

@Controller
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger("PostController.class");

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
    public String newPost(Model model, @PathVariable int id, Model model2, Model model3, Model model4) {
        model2.addAttribute("subreddit", subredditServiceM.getSubredditById(id));
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

            model.setName(name);
            model.setImage(image);
            model.setSubreddit(subreddit.getIdsubreddit());
            model.setDescription(description);
            model.setUsername(u.getName());


            int saveImage = postServiceM.saveImage(model);
            if (mimeType.equals("image/png") || image.length == 0 || mimeType.equals("image/jpeg") || mimeType.equals("example/example") || mimeType.equals(",") || mimeType.equals("video/webm") || mimeType.equals("image/gif")) {
                return "redirect:/subreddit/post/" + model.getPostid();
            } else {
                return "error";
            }
        } catch (Exception e) {
            logger.error("ERROR", e);
            return "error";
        }
    }


    @GetMapping("/subreddit/post/{id}")
    public String getPostview(Model model, @PathVariable int id) throws UnsupportedEncodingException {
        model.addAttribute("post", postServiceM.getPostById(id));
        model.addAttribute("subreddits", subredditServiceM.listAllSubreddits());
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", commentServiceM.listAllComments());

        Post imagesObj = postServiceM.getPostById(id);
        model.addAttribute("id", imagesObj.getPostid());
        model.addAttribute("name", imagesObj.getName());
        byte[] encode = java.util.Base64.getEncoder().encode(imagesObj.getImage());
        model.addAttribute("imagee", new String(encode, "UTF-8"));

        return "viewPost";
    }

    @GetMapping("/subreddit/post/upvote/{id}")
    public String upKarma(@PathVariable("id") int id, Model model, Model model2, Post p) {
        int x = postServiceM.getPostById(id).getPostid();
        p = postServiceM.getPostById(id);
        int karma = p.getKarma();
        karma++;
        p.setKarma(karma);

        postServiceM.savePost(p);

        return "redirect:/subreddit/post/" + x;

    }

    @GetMapping("/subreddit/post/delete/{id}")
    public String deleteP(@PathVariable("id") int id, Model model, Model model2) {
        int x = postServiceM.getPostById(id).getSubreddit();
        postServiceM.deletePost(id);
        return "redirect:/subreddit/" + x;

    }
}
