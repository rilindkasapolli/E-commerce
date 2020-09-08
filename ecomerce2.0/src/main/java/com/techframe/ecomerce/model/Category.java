package com.techframe.ecomerce.model;


import javax.persistence.*;
import java.util.List;

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
}
