package reddit.example.simpleforumgmail.services;

import reddit.example.simpleforumgmail.models.Comment;
import reddit.example.simpleforumgmail.models.Post;

public interface CommentService {

    Iterable<Comment> listAllComments();

    Comment getCommentById(Integer id);

    Comment saveComment(Comment comment);


}
