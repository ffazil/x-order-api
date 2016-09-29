package com.x.order.order;

import com.x.order.product.Product;

import static com.x.order.order.ProductFixture.product1;
import static com.x.order.order.ProductFixture.product2;
import static com.x.order.order.ProductFixture.product3;

/**
 * Created by khka on 9/29/2016.
 */
public class OrderLineFixture {
    public static OrderLine orderLine1(){
        Product p1 = product1();
        return new OrderLine(p1,  3);
    }
    public static OrderLine orderLine2(){
        Product p2 = product2();
        return new OrderLine(p2, 3);
    }
    public static OrderLine orderLine3(){
        Product p3 = product3();
        return new OrderLine(p3,  3);
    }
}
