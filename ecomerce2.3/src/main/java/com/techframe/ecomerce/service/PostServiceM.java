package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Post;
import com.techframe.ecomerce.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceM implements PostService {


    private PostRepository postRepository;

    @Autowired
    private void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Iterable<Post> listAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }


    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
