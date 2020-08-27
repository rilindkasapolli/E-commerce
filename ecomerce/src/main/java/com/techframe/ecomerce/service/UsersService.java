package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Product;
import com.techframe.ecomerce.model.Users;

public interface UsersService  {
    Iterable<Users> listAllProducts();

    Users getUsersById(Integer id);

    Users saveUsers(Users users);

    Users getUsersByUsername(String username);

}
