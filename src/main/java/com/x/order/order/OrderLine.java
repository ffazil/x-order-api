package com.x.order.order;

import com.x.order.AbstractEntity;
import com.x.order.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by samo on 9/26/2016.
 */

@Getter
@AllArgsConstructor
public class OrderLine extends AbstractEntity{

    private final Product product;
    private final Double unitPrice;
    private final int quantity;


    protected OrderLine(){
        this(null,null,0);
    }
}
