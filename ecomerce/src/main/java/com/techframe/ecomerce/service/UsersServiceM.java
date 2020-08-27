package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Product;
import com.techframe.ecomerce.model.Users;
import com.techframe.ecomerce.repository.ProductRepository;
import com.techframe.ecomerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceM implements UsersService{

    private UsersRepository usersRepository;

    @Autowired
    public void setUsersRepositoryRepository(UsersRepository usersRepository) {

        this.usersRepository = usersRepository;
    }


    @Override
    public Iterable<Users> listAllProducts() {

        return usersRepository.findAll();
    }



    @Override
    public Users getUsersById(Integer id) {

        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public Users saveUsers(Users users) {

        return usersRepository.save(users);
    }

    @Override
    public Users getUsersByUsername(String username) {
        return usersRepository.findUsersByUsername(username);
    }

    public void deleteProduct(Integer id) {
        usersRepository.deleteById(id);
    }
}
