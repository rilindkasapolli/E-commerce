package com.techframe.ecomerce.repository;

import com.techframe.ecomerce.model.Product;
import com.techframe.ecomerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Integer> {


}
