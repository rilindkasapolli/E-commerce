package com.techframe.ecomerce.repository;

import com.techframe.ecomerce.model.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Integer> {


}
