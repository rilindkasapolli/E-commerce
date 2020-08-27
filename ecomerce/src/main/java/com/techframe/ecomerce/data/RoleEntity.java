package com.techframe.ecomerce.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "role", schema = "ecommerce", catalog = "")
public class RoleEntity {
    private Integer idrole;
    private String namerole;

    @Id
    @Column(name = "idrole", nullable = false)
    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    @Basic
    @Column(name = "namerole", nullable = true, length = 45)
    public String getNamerole() {
        return namerole;
    }

    public void setNamerole(String namerole) {
        this.namerole = namerole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(idrole, that.idrole) &&
                Objects.equals(namerole, that.namerole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idrole, namerole);
    }
}
