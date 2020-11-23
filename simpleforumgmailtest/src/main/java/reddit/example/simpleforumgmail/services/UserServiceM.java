package reddit.example.simpleforumgmail.services;


import org.apache.tika.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.repository.RoleRepository;
import reddit.example.simpleforumgmail.repository.UserRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private static final Logger logger = LoggerFactory.getLogger("DocStorageService.class");

    public int saveImage(User model) {
        try {
            usersRepository.save(model);
            return 1;
        } catch (Exception e) {
            logger.error("ERROR", e);
            return 0;
        }
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
    public User save(User registrationDto)  {

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
    public User findByEmailIgnoreCase(String email) {
        return usersRepository.findByEmail(email);
    }

    public void deleteProduct(Integer id) {
        usersRepository.deleteById(id);
    }
}
