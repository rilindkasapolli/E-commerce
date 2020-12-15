package reddit.example.simpleforumgmail.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reddit.example.simpleforumgmail.models.CommentLikes;

@Repository
public interface CommentLikesRepository extends CrudRepository<CommentLikes, Integer> {

    CommentLikes getCommentLikesByUser(Integer id);
    CommentLikes getCommentLikesByComment(Integer id);
    CommentLikes getCommentLikesByUserAndComment(Integer user, Integer comment);

}