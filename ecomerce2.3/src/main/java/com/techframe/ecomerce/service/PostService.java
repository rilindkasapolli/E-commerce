package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Category;
import com.techframe.ecomerce.model.Post;

public interface PostService {

    Iterable<Post> listAllPosts();

    Post getPostById(Integer id);


    Post savePost(Post Post);
}


