package com.x.order.customer;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author firoz
 * @since 26/09/16
 */
@Slf4j
@Service
public class CustomerInitializer {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerInitializer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

        if(customerRepository.count() == 0){

            Customer suresh = new Customer("Suresh", "Maurya", "suresh.maurya1@gmail.com");
            suresh = customerRepository.save(suresh);

            log.info("Added customer {}", suresh.getFirstName());
        }
    }
}
