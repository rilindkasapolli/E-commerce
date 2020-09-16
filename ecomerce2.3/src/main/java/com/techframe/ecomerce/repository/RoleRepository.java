package com.techframe.ecomerce.repository;

import com.techframe.ecomerce.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findRoleByName(String name);

    List<Role> findAll();
}
