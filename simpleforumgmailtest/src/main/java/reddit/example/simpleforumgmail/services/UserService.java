package reddit.example.simpleforumgmail.services;


import reddit.example.simpleforumgmail.models.User;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserService {
    Iterable<User> listAllUsers();
    User getUsersById(Integer id);
    User saveUsers(User users);
    User save(User registrationDto) throws IOException;
    User findByEmailIgnoreCase(String email);


}
