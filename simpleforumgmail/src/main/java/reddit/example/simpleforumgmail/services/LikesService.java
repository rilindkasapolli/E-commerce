package reddit.example.simpleforumgmail.services;

import reddit.example.simpleforumgmail.models.Likes;

public interface LikesService {

    Iterable<Likes> listAllLikes();



    Likes saveLikes(Likes likes);
    Likes getLikesByUserIdAndPostId(Integer id, Integer id2);

}
