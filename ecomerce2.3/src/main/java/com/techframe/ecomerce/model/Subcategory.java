package com.techframe.ecomerce.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "subcategory1")
@Table(name = "subcategory1")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int subcategoryid;
    private String name;
    private String description;

    @Column(name = "category_id")
    private int category;


    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private Set<Product> products;


    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSubcategoryid() {
        return subcategoryid;
    }

    public void setSubcategoryid(int subcategoryid) {
        this.subcategoryid = subcategoryid;
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

}

