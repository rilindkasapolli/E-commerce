package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Role;
import com.techframe.ecomerce.model.User;
import com.techframe.ecomerce.web.UserRegistrationDto;

public interface UserService {
    Iterable<User> listAllUsers();

    User getUsersById(Integer id);

    User saveUsers(User users);
    User save(UserRegistrationDto registrationDto);

   User getUsersByUsername(String username);




}
