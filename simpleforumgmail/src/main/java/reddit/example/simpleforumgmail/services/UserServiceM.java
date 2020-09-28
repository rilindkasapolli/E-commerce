package reddit.example.simpleforumgmail.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.models.Role;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.repository.RoleRepository;
import reddit.example.simpleforumgmail.repository.UserRepository;
import reddit.example.simpleforumgmail.web.UserRegistrationDto;

import java.util.ArrayList;
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


        // Role role = roleRepository.findRoleByName("ROLE_USER");






        User user = new User();
        user.setName(registrationDto.getName());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getEmail());
        user.setRole(1);


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
