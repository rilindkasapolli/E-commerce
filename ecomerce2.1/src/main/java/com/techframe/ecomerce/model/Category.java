package com.techframe.ecomerce.model;


import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryid;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "categoryList")
    private List<Product> products;

    @ManyToMany(mappedBy = "category")
    private List<Subcategory> subcategories;


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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }



    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }


}
