package com.techframe.ecomerce.repository;

import com.techframe.ecomerce.model.Role;
import com.techframe.ecomerce.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByName(String username);

    User findByEmail(String email);

}
