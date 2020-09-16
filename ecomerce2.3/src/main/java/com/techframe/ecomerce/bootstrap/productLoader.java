package com.techframe.ecomerce.bootstrap;

import com.techframe.ecomerce.model.Product;
import com.techframe.ecomerce.model.Role;
import com.techframe.ecomerce.model.Subcategory;
import com.techframe.ecomerce.model.User;
import com.techframe.ecomerce.repository.ProductRepository;
import com.techframe.ecomerce.repository.SubcategoryRepository;
import com.techframe.ecomerce.service.CategoryServiceM;
import com.techframe.ecomerce.service.SubcategoryServiceM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class productLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;
    private Logger log = LogManager.getLogger(productLoader.class);
    private List<Role> rolelist;
    @Autowired
    private CategoryServiceM categoryServiceM;
    @Autowired
    private SubcategoryServiceM subcategoryServiceM;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    private SubcategoryRepository subcategoryRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}
