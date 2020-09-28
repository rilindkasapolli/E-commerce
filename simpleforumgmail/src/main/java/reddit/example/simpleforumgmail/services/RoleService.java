package reddit.example.simpleforumgmail.services;



import reddit.example.simpleforumgmail.models.Role;
import reddit.example.simpleforumgmail.models.User;

import java.util.List;


public interface RoleService {

    List<Role> listAllRoles();


    Role getRoleByName(String username);
    // Role getRoleNameByUser()

    Role getRoleById(Integer id);
}
