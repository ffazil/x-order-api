package com.x.order.Order;

import com.x.order.AbstractEntity;
import lombok.Getter;

/**
 * Created by samo on 9/26/2016.
 */

@Getter
public class OrderLine extends AbstractEntity{
    private String product;
    private Float unitPrice;
    private int quantity;

    public OrderLine(String product, Float unitPrice, int quantity) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    protected OrderLine(){
        this(null,null,0);
    }
}
