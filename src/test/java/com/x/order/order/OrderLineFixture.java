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
        OrderLine o1 =  new OrderLine(p1,3);
        o1.calculateSubTotal();
        return o1;
    }
    public static OrderLine orderLine2(){
        Product p2 = product2();
        OrderLine o2 =  new OrderLine(p2,2);
        o2.calculateSubTotal();
        return o2;
    }
    public static OrderLine orderLine3(){
        Product p3 = product3();
        OrderLine o3 =  new OrderLine(p3,5);
        o3.calculateSubTotal();
        return o3;
    }
}
