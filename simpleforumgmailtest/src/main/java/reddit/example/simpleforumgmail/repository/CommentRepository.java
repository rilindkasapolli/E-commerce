package reddit.example.simpleforumgmail.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reddit.example.simpleforumgmail.models.Comment;
import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    Comment getCommentByUser(Integer id);
    List<Comment> findTopByOrderByCommentid();
    List<Comment> findByUsernameContainingOrderByTimeCreatedDesc(String username);

}
