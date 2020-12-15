package reddit.example.simpleforumgmail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.models.CommentLikes;
import reddit.example.simpleforumgmail.repository.CommentLikesRepository;

@Service
public class CommentLikesServiceM implements CommentLikesService {
    @Autowired
    public CommentLikesRepository commentLikesRepository;
    @Override
    public Iterable<CommentLikes> listAllCommentLikes() {
        return commentLikesRepository.findAll();
    }



    @Override
    public CommentLikes getCommentLikesByUserId(Integer id) {
        return commentLikesRepository.getCommentLikesByUser(id);
    }



    @Override
    public CommentLikes saveCommentLikes(CommentLikes commentLikes) {
        return commentLikesRepository.save(commentLikes);
    }

    @Override
    public CommentLikes getCommentLikesByUserIdAndCommentId(Integer id, Integer id2) {
        return commentLikesRepository.getCommentLikesByUserAndComment(id,id2);
    }
}
