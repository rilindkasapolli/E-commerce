package com.techframe.ecomerce.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idproduct;

    @Version
    private Integer version;
    private String productName;
    private String description;
    private String imageUrl;
    private BigDecimal price;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="category_product",
            joinColumns={@JoinColumn(name="product_id", referencedColumnName="idproduct")},
            inverseJoinColumns={@JoinColumn(name="category_id", referencedColumnName="categoryid")})
    private List<Category> categoryList;

    public Product() {
    }



    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Integer id) {
        this.idproduct = id;
    }



    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
