package com.techframe.ecomerce.repository;

import com.techframe.ecomerce.model.Post;
import com.techframe.ecomerce.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {


    List<Post> findAll();
}
