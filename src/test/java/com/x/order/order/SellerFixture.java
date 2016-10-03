package com.x.order.order;

import com.x.order.seller.Seller;

/**
 * Created by khka on 9/29/2016.
 */
public class SellerFixture {
    public static Seller seller1(){
        return new Seller("Seller1", "SellerLastName", "seller@gmail.com", "1234UY6");
    }
}
