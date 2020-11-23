package reddit.example.simpleforumgmail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.models.Likes;
import reddit.example.simpleforumgmail.repository.LikesRepository;

@Service
public class LikesServiceM implements LikesService {
    @Autowired
    private LikesRepository likesRepository;

    @Override
    public Iterable<Likes> listAllLikes() {
        return likesRepository.findAll();
    }

    @Override
    public Likes saveLikes(Likes likes) {
        return likesRepository.save(likes);
    }

    @Override
    public Likes getLikesByUserIdAndPostId(Integer id, Integer id2) {
         return likesRepository.getLikesByUserAndPost(id,id2);
    }
}
