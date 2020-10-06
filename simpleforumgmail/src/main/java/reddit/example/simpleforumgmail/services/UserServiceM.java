package reddit.example.simpleforumgmail.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.repository.RoleRepository;
import reddit.example.simpleforumgmail.repository.UserRepository;

import java.util.Objects;


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

    public boolean exists(User u) {
        if (u.getId() == null) {
            return false;
        } else
            return true;
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
    public User save(User registrationDto) {


        // Role role = roleRepository.findRoleByName("ROLE_USER");


        User user = new User();
        user.setName(registrationDto.getName());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getEmail());
        user.setRole(1);

        user.setEnabled(false);


        return usersRepository.save(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserServiceM that = (UserServiceM) o;
        return Objects.equals(roleRepository, that.roleRepository) &&
                Objects.equals(bCryptPasswordEncoder, that.bCryptPasswordEncoder) &&
                Objects.equals(usersRepository, that.usersRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleRepository, bCryptPasswordEncoder, usersRepository);
    }

    @Override
    public User getUsersByUsername(String username) {
        return usersRepository.findUserByName(username);
    }

    @Override
    public User findByEmailIgnoreCase(String email) {
        return usersRepository.findByEmail(email);
    }

    public void deleteProduct(Integer id) {
        usersRepository.deleteById(id);
    }
}
