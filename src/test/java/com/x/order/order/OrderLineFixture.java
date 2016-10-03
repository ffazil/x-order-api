package com.x.order.order;

import com.x.order.product.Product;

import static com.x.order.order.ProductFixture.product1;
import static com.x.order.order.ProductFixture.product2;
import static com.x.order.order.ProductFixture.product3;

/**
 * Created by khka on 9/29/2016.
 */
public class OrderLineFixture {
    public static OrderLine orderLine( Product p, Integer quantity){
        OrderLine o1 =  new OrderLine(p,quantity);
        o1.calculateSubTotal();
        return o1;
    }
}
