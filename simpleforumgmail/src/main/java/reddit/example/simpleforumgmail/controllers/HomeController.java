package reddit.example.simpleforumgmail.controllers;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reddit.example.simpleforumgmail.models.Comment;
import reddit.example.simpleforumgmail.models.Post;
import reddit.example.simpleforumgmail.models.Subreddit;
import reddit.example.simpleforumgmail.repository.PostRepository;
import reddit.example.simpleforumgmail.services.CommentServiceM;
import reddit.example.simpleforumgmail.services.PostServiceM;
import reddit.example.simpleforumgmail.services.SubredditServiceM;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.stream.StreamSupport;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger("HomeController.class");

    public PostServiceM postServiceM;

    @Autowired
    public void setPostServiceM(PostServiceM postServiceM){
        this.postServiceM=postServiceM;
    }
    @Autowired
    public SubredditServiceM subredditServiceM;

    @Autowired
    public CommentServiceM commentServiceM;
    @Autowired
    public PostRepository postRepository;

    @GetMapping("/")
    public  String index(Model model,Model model2, Model model3, Model model4)  {

        Iterable<Post> post = postServiceM.listAllPosts();

        model.addAttribute("posts", postServiceM.listAllPosts());
        model2.addAttribute("subreddits",subredditServiceM.listAllSubreddits());
        model3.addAttribute("subredditnumber", StreamSupport.stream(post.spliterator(), false).count());
        model4.addAttribute("topPosts", postRepository.findTop5ByOrderByKarmaDesc());


        return "index";
    }


    @GetMapping("/subreddit/{id}/newPost")
    public String newPost(Model model, @PathVariable int id, Model model2, Model model3,Model model4){
        model2.addAttribute("subreddit", subredditServiceM.getSubredditById(id));
        model.addAttribute("post", new Post());



        return "addPost";
    }

    //@PostMapping("/uploadFile")
    //public String uploadMultipleFile(@RequestParam("files") MultipartFile[] files){
      //  for (MultipartFile file : files){
        //    docStorageService.saveFile(file);
       // }
       // return "redirect:/docu";
   // }



    @PostMapping("/newpost")
    public String savePost(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, @RequestParam("subreddit") Subreddit subreddit, @RequestParam("description") String description) {
        try {
            logger.info("Name= " + name);
            byte[] image = file.getBytes();
            Tika tika = new Tika();
            String mimeType = tika.detect(image);

            Post model = new Post();

            model.setName(name);
            model.setImage(image);
            model.setSubreddit(subreddit.getIdsubreddit());
            model.setDescription(description);
            int saveImage = postServiceM.saveImage(model);
            if (mimeType.equals("image/png") || image.length==0 ||  mimeType.equals("image/jpeg") ||mimeType.equals("example/example")|| mimeType.equals(",") || mimeType.equals("video/webm") || mimeType.equals("image/gif")) {
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
    public String getPostview(Model model,@PathVariable int id) throws UnsupportedEncodingException {
        model.addAttribute("post", postServiceM.getPostById(id));
        model.addAttribute("subreddits",subredditServiceM.listAllSubreddits());
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", commentServiceM.listAllComments());


        Post imagesObj = postServiceM.getPostById(id);
        model.addAttribute("id", imagesObj.getPostid());
        model.addAttribute("name", imagesObj.getName());
        byte[] encode = java.util.Base64.getEncoder().encode(imagesObj.getImage());
        model.addAttribute("imagee", new String(encode, "UTF-8"));

        return "viewPost";
    }
    @GetMapping("/subreddit/new")
    public String newSubreddit(Model model){
        model.addAttribute("subreddit", new Subreddit());
        return "addSubreddit";
    }
    @PostMapping("/newsubreddit")
    public String saveSubreddit(Subreddit p) {
        subredditServiceM.saveSubreddit(p);
        return "redirect:/subreddit/" + p.getIdsubreddit();
    }
    @GetMapping("/subreddit/{id}")
    public String viewSubreddit(Model model, @PathVariable int id, Model model2,Model model3){
        model3.addAttribute("subreddits", subredditServiceM.listAllSubreddits());
        model.addAttribute("subreddit", subredditServiceM.getSubredditById(id));
        model2.addAttribute("posts", postServiceM.listAllPosts());
        return "viewSubreddit";
    }
    @PostMapping("/newcomment")
    public String saveComment(Comment p) {


        commentServiceM.saveComment(p);
        return "redirect:/subreddit/post/" + p.getPost();
    }



    @GetMapping("/subreddit/post/upvote/{id}")
    public String upKarma(@PathVariable("id") int id, Model model, Model model2, Post p) {
        int x = postServiceM.getPostById(id).getPostid();

         p = postServiceM.getPostById(id);
        int karma = p.getKarma();

        karma++;

        p.setKarma(karma);

        postServiceM.savePost(p);

        return "redirect:/subreddit/post/" + x ;

    }


    //@GetMapping("/subreddit/post/{id}/newPost")
    //public String newComment(Model model, @PathVariable int id, Model model2){
      //  model2.addAttribute("post", postServiceM.getPostById(id));
        //model.addAttribute("post", new Post());
        //return "viewPost";
    //}


    @DeleteMapping("deletesubreddit")
    public void deleteSubreddit(int id) {

        subredditServiceM.deleteSubreddit(id);
    }

    @GetMapping("/subreddit/delete/{id}")
    public String deleteS(@PathVariable("id") int id) {
        subredditServiceM.deleteSubreddit(id);
        return "redirect:/";

    }



    @GetMapping("/subreddit/post/delete/{id}")
    public String deleteP(@PathVariable("id") int id, Model model, Model model2) {
        int x = postServiceM.getPostById(id).getSubreddit();

        postServiceM.deletePost(id);
        return "redirect:/subreddit/" + x ;

    }


    @GetMapping("/subreddit/post/comment/delete/{id}")
    public String deleteC(@PathVariable("id") int id) {
        int x = commentServiceM.getCommentById(id).getPost();
        commentServiceM.deleteComment(id);
        return "redirect:/subreddit/post/" + x;

    }


}
