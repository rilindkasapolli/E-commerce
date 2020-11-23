package reddit.example.simpleforumgmail.services;

import reddit.example.simpleforumgmail.models.CommentLikes;

public interface CommentLikesService {

    Iterable<CommentLikes> listAllCommentLikes();
    CommentLikes getCommentLikesByUserId(Integer id);
    CommentLikes saveCommentLikes(CommentLikes CommentLikes);
    CommentLikes getCommentLikesByUserIdAndCommentId(Integer id, Integer id2);

}
