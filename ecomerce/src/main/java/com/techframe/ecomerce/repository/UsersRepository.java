package com.techframe.ecomerce.repository;

import com.techframe.ecomerce.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    public Users findUsersByUsername(String username);
}
