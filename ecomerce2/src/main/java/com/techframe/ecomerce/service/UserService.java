package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Role;
import com.techframe.ecomerce.model.User;

public interface UserService {
    Iterable<User> listAllUsers();

    User getUsersById(Integer id);

    User saveUsers(User users);

   User getUsersByUsername(String username);


}
