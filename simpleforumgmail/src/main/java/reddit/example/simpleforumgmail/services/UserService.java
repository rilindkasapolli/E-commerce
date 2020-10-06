package reddit.example.simpleforumgmail.services;


import reddit.example.simpleforumgmail.models.User;

public interface UserService {
    Iterable<User> listAllUsers();

    User getUsersById(Integer id);

    User saveUsers(User users);

    User save(User registrationDto);


    User getUsersByUsername(String username);

    User findByEmailIgnoreCase(String email);


}
