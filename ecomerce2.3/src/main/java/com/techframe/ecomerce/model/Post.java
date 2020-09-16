package com.techframe.ecomerce.model;


import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity(name = "post")

public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postid;


    private String postdescription;
    @Nullable
    private Integer postrating;


    @Column(name = "product_id")
    private int product;

    public Post() {

    }

    public int getPostrating() {
        return postrating;
    }

    public void setPostrating(int postrating) {
        this.postrating = postrating;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }


    public String getPostdescription() {
        return postdescription;
    }

    public void setPostdescription(String postdescription) {
        this.postdescription = postdescription;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}
