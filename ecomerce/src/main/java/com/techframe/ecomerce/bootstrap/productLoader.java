package com.techframe.ecomerce.bootstrap;

import com.techframe.ecomerce.model.Product;
import com.techframe.ecomerce.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class productLoader implements ApplicationListener<ContextStartedEvent> {

    private ProductRepository productRepository;
    private Logger log = LogManager.getLogger(productLoader.class);

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
        log.info("Saved Shirt - id: " + shirt.getId());


    }

}
