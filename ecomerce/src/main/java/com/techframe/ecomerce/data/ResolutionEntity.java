package com.techframe.ecomerce.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "resolution", schema = "ecommerce", catalog = "")
public class ResolutionEntity {
    private Integer id;
    private String text;
    private String owner;
    private Byte completed;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text", nullable = true, length = 45)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "owner", nullable = true, length = 45)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "completed", nullable = true)
    public Byte getCompleted() {
        return completed;
    }

    public void setCompleted(Byte completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResolutionEntity that = (ResolutionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(text, that.text) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(completed, that.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, owner, completed);
    }
}
