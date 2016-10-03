package com.x.order.order;

/**
 * Created by khka on 9/29/2016.
 */
public enum OrderStatus {
    Placed(1),Payed(2),Delivered(3);

    public final int statusId;

    OrderStatus(int statusId){
        this.statusId=statusId;
    }
}
