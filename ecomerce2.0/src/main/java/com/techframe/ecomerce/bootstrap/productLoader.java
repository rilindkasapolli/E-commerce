package com.techframe.ecomerce.bootstrap;

import com.techframe.ecomerce.model.Product;
import com.techframe.ecomerce.model.Role;
import com.techframe.ecomerce.model.User;
import com.techframe.ecomerce.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class productLoader implements ApplicationListener<ContextStartedEvent> {

    private ProductRepository productRepository;
    private Logger log = LogManager.getLogger(productLoader.class);
    private List<Role> rolelist;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        Product shirt = new Product();
        shirt.setDescription("aaaaaaaaaaa");
        shirt.setPrice(new BigDecimal("12"));
        shirt.setImageUrl("https://google.com");
        shirt.setProductName("bbbbb");
        productRepository.save(shirt);
        log.info("Saved Shirt - id: " + shirt.getIdproduct());



        Role r = new Role();
        r.setId(4);
        r.setName("ROLE_ADMIN");
        rolelist.add(r);

        User user = new User();
        user.setId(20);
        user.setEmail("asadf");
        user.setName("agasdg");
        user.setPassword("asdf");
        user.setRoles(rolelist);

        System.out.println("yo");




    }

}
