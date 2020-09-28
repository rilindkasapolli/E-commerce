package reddit.example.simpleforumgmail.services;


import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.web.UserRegistrationDto;

public interface UserService {
    Iterable<User> listAllUsers();

    User getUsersById(Integer id);

    User saveUsers(User users);

    User save(UserRegistrationDto registrationDto);

    User getUsersByUsername(String username);


}
