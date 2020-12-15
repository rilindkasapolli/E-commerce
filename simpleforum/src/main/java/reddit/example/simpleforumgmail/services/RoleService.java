package reddit.example.simpleforumgmail.services;

import reddit.example.simpleforumgmail.models.Role;
import java.util.List;

public interface RoleService {

    List<Role> listAllRoles();


    Role getRoleByName(String username);


    Role getRoleById(Integer id);
}
