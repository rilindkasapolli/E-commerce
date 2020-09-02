package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Role;
import com.techframe.ecomerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceM implements RoleService {

  private  RoleRepository roleRepository;
  @Autowired
  private void setRoleRepository(RoleRepository roleRepository){
      this.roleRepository=roleRepository;
  }


    @Override
    public List<Role> listAllRoles() {
        return roleRepository.findAll();
    }



    @Override
    public Role getRoleByName(String username) {
        return roleRepository.findRoleByName(username);
    }
}
