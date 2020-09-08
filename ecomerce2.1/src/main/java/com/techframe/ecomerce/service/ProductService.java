package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Product;

public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

}
