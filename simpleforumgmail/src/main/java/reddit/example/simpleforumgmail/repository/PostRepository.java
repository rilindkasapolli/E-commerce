package reddit.example.simpleforumgmail.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import reddit.example.simpleforumgmail.models.Post;

import java.util.List;


public interface PostRepository extends CrudRepository<Post, Integer> {


    @Query("SELECT p FROM post p WHERE CONCAT(p.name, ' ') LIKE %?1%")
    List<Post> search(String keyword);

    Post findPostByName(String name);

    List<Post> findTop5ByOrderByKarmaDesc();


}
