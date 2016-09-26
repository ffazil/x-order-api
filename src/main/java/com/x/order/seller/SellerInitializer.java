package com.x.order.seller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author firoz
 * @since 26/09/16
 */
@Slf4j
@Service
public class SellerInitializer {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerInitializer(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;

        if(sellerRepository.count() == 0){

            Seller gopal = new Seller("Gopal", "Nayak", "gopal.nayak@gmail.com","TAEDSD232");
            gopal = sellerRepository.save(gopal);

            log.info("Added seller {}", gopal.getFirstName());
        }
    }
}
