package com.x.order.order;

import com.x.order.customer.Customer;

/**
 * Created by khka on 9/29/2016.
 */
public class CustomerFixture {
    public static Customer customer1(){
        return new Customer("Customer1", "CustomerSurName", "test1@gmai.com");
    }
}
