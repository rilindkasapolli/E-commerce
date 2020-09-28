package reddit.example.simpleforumgmail.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.example.simpleforumgmail.models.Role;
import reddit.example.simpleforumgmail.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceM implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    private void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> listAllRoles() {
        return roleRepository.findAll();
    }


    @Override
    public Role getRoleByName(String username) {
        return roleRepository.findRoleByName(username);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}
