package reddit.example.simpleforumgmail.repository;


import org.springframework.data.repository.CrudRepository;
import reddit.example.simpleforumgmail.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByName(String username);

    User findByEmail(String email);
    User findByEmailIgnoreCase(String email);
}
