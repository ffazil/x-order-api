package com.x.order.order;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by khka on 9/29/2016.
 */
public interface OrderRepository extends PagingAndSortingRepository<ProductsOrder,Long> {
}
