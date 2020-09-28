package reddit.example.simpleforumgmail.repository;

import org.springframework.data.repository.CrudRepository;

import reddit.example.simpleforumgmail.models.Post;

import java.util.List;


public interface PostRepository extends CrudRepository<Post,Integer> {
    Post findPostByName(String name);

   List <Post> findTop5ByOrderByKarmaDesc();

}
