package reddit.example.simpleforumgmail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.models.Comment;
import reddit.example.simpleforumgmail.repository.CommentRepository;

@Service
public class CommentServiceM implements CommentService {
    @Autowired
    public CommentRepository commentRepository;

    @Override
    public Iterable<Comment> listAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }



    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

}
