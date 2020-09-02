package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.User;
import com.techframe.ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceM implements UserService {

    private UserRepository usersRepository;

    @Autowired
    public void setUsersRepositoryRepository(UserRepository usersRepository) {

        this.usersRepository = usersRepository;
    }


    @Override
    public Iterable<User> listAllUsers() {

        return usersRepository.findAll();
    }



    @Override
    public User getUsersById(Integer id) {

        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUsers(User users) {

        return usersRepository.save(users);
    }

    @Override
    public User getUsersByUsername(String username) {
        return usersRepository.findUserByName(username);
    }

    public void deleteProduct(Integer id) {
        usersRepository.deleteById(id);
    }
}
