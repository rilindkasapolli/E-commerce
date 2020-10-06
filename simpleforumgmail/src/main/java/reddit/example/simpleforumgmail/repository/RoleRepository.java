package reddit.example.simpleforumgmail.repository;


import org.springframework.data.repository.CrudRepository;
import reddit.example.simpleforumgmail.models.Role;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findRoleByName(String name);

    List<Role> findAll();
}
