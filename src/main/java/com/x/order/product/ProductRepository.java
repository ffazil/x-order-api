package com.x.order.product;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by masu on 26-Sep-16.
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
