package reddit.example.simpleforumgmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reddit.example.simpleforumgmail.models.Likes;


import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {
    Likes getLikesByUser(Integer id);
    Likes getLikesByPost(Integer id);
    Likes getLikesByUserAndPost(Integer user, Integer post);
     List<Likes> findAllByPost(Integer post);
}
