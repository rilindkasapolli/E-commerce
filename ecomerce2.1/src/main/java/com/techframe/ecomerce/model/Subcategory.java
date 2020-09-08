package com.techframe.ecomerce.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="subcategory")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subcategoryid;
    private String name;
    private String description;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="category_subcategory",
            joinColumns={@JoinColumn(name="subcategory_id", referencedColumnName="subcategoryid")},
            inverseJoinColumns={@JoinColumn(name="category_id", referencedColumnName="categoryid")})
    private List<Category> category;



    @ManyToMany(mappedBy = "subcategoryList")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public List<Category> getCategories() {
        return category;
    }

    public void setCategories(List<Category> category) {
        this.category = category;
    }
}

