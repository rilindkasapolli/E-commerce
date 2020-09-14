package com.techframe.ecomerce.model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "product1")

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




    @Column(name="subcategory_id")
    private int subcategory;





    public Product() {
    }


    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
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


}
