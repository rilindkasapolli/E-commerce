package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Role;
import com.techframe.ecomerce.model.User;
import com.techframe.ecomerce.repository.RoleRepository;
import com.techframe.ecomerce.repository.UserRepository;
import com.techframe.ecomerce.web.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceM implements UserService {
    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserRepository usersRepository;

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
    public User save(UserRegistrationDto registrationDto) {
        List<Role> roles = new ArrayList<Role>();


       // Role role = roleRepository.findRoleByName("ROLE_USER");


        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(role);


        User user = new User();
        user.setName(registrationDto.getName());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getEmail());
        user.setRoles(roles);




        return usersRepository.save(user);
    }

    @Override
    public User getUsersByUsername(String username) {
        return usersRepository.findUserByName(username);
    }

    public void deleteProduct(Integer id) {
        usersRepository.deleteById(id);
    }
}
