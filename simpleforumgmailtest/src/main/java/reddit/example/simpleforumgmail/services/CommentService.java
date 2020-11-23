package reddit.example.simpleforumgmail.services;

import reddit.example.simpleforumgmail.models.Comment;


public interface CommentService {

    Iterable<Comment> listAllComments();

    Comment getCommentById(Integer id);

    Comment saveComment(Comment comment);


}
