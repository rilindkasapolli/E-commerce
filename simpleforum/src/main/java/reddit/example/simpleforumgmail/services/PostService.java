package reddit.example.simpleforumgmail.services;

import reddit.example.simpleforumgmail.models.Post;

public interface PostService {

    Iterable<Post> listAllPosts();

    Post getPostById(Integer id);

    Post savePost(Post post);

    Post getPostByName(String name);

}
