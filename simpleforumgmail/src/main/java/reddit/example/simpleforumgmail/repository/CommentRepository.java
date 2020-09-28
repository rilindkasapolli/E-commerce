package reddit.example.simpleforumgmail.repository;

import org.springframework.data.repository.CrudRepository;
import reddit.example.simpleforumgmail.models.Comment;
import reddit.example.simpleforumgmail.models.Post;

public interface CommentRepository extends CrudRepository<Comment,Integer> {


}
