package com.x.order.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by masu on 26-Sep-16.
 */
@Slf4j
@Service
public class ProductInitializer {

    private final ProductRepository productRepository;

    @Autowired
    public ProductInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;

        if(productRepository.count() == 0){
            Product product = new Product("Reservation", "Evening Reservation",534.00);
            product = productRepository.save(product);

            log.info("Product saved - "+product.getName());
        }
    }
}
