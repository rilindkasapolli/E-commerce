package reddit.example.simpleforumgmail.services;

import reddit.example.simpleforumgmail.models.Post;
import reddit.example.simpleforumgmail.models.Subreddit;

public interface PostService {

    Iterable<Post> listAllPosts();

    Post getPostById(Integer id);

    Post savePost(Post post);

    Post getPostByName(String name);

}
