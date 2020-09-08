package com.techframe.ecomerce.model;

import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="role")
public class Role
{
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;


    private String name;

    public Role() {
    }

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }



    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }



}