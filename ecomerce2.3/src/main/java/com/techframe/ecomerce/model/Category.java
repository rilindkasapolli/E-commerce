package com.techframe.ecomerce.model;


import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity(name = "category1")
@Table(name = "category1")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int categoryid;
    private String name;
    private String description;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private Set<Subcategory> subcategories;


    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int id) {
        this.categoryid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
