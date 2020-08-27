package com.techframe.ecomerce.data;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "category", schema = "ecommerce", catalog = "")
public class CategoryEntity {
    private Integer idcategory;
    private String name;
    private Byte status;
    private Integer parentId;
    private CategoryEntity categoryByParentId;

    @Id
    @Column(name = "idcategory", nullable = false)
    public Integer getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "parentId", nullable = true)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(idcategory, that.idcategory) &&
                Objects.equals(name, that.name) &&
                Objects.equals(status, that.status) &&
                Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcategory, name, status, parentId);
    }

    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "idcategory", table = "category")
    public CategoryEntity getCategoryByParentId() {
        return categoryByParentId;
    }

    public void setCategoryByParentId(CategoryEntity categoryByParentId) {
        this.categoryByParentId = categoryByParentId;
    }
}
