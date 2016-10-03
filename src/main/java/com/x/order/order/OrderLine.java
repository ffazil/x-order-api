package com.x.order.order;

import com.x.order.AbstractEntity;
import com.x.order.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.engine.spi.CascadingAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by samo on 9/26/2016.
 */

@Getter
@Entity
@NoArgsConstructor(force = true)
public class OrderLine extends AbstractEntity{

    @ManyToOne
    private final Product product;
    private final Double unitPrice;
    private final Integer quantity;
    private Double subtotal;

    protected OrderLine(Product product, Integer quantity){
        this.product=product;
        this.unitPrice=product.getUnitPrice();
        this.quantity=quantity;
    }

    protected void calculateSubTotal(){
        this.subtotal=this.unitPrice*this.quantity;
    }


}
