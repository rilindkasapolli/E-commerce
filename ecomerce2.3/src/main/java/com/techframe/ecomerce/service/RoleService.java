package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Role;

import java.util.List;


public interface RoleService {

    List<Role> listAllRoles();


    Role getRoleByName(String username);
    // Role getRoleNameByUser()
}
