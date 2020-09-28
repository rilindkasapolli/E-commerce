package reddit.example.simpleforumgmail.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.models.Post;
import reddit.example.simpleforumgmail.repository.PostRepository;

import java.util.Optional;


@Service
public class PostServiceM implements PostService{
    @Autowired
    private PostRepository postRepository;


    @Override
    public Iterable<Post> listAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostByName(String name) {
        return postRepository.findPostByName(name);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }


    private static final Logger logger = LoggerFactory.getLogger("DocStorageService.class");
    @Autowired
    private PostRepository dao;

    public int saveImage(Post model) {
        try {
            dao.save(model);
            return 1;
        } catch (Exception e) {
            logger.error("ERROR", e);
            return 0;
        }
    }

    public Post getImages(Integer id) {
        Optional findById = dao.findById(id);
        if (findById.isPresent()) {
            Post getImageDetails = (Post) findById.get();
            logger.info("id= " + getImageDetails.getPostid() + " name= " + getImageDetails.getName());
            return getImageDetails;
        } else {
            return null;
        }
    }
}
