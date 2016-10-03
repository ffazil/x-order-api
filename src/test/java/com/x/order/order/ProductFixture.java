package com.x.order.order;

import com.x.order.product.Product;

/**
 * Created by khka on 9/29/2016.
 */
public class ProductFixture {
    public static Product product1(){
        return new Product("laptop", "NoNeed",54327.00);
    }
    public static Product product2(){
        return new Product("mouse", "NoNeed",346.50);
    }

    public static Product product3(){
        return new Product("keyboard", "NoNeed",543.00);
    }
}
