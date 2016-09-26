package com.x.order.customer;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author firoz
 * @since 26/09/16
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>{
}
