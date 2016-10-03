package com.x.order.order;

import com.x.order.AbstractEntity;
import com.x.order.customer.Customer;
import com.x.order.seller.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by khka on 9/29/2016.
 */
@Entity
@Getter
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class ShippingOrder extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private final List<OrderLine> orderLines;

    private Double totalPrice;

    @OneToOne
    private final Seller seller;

    @OneToOne
    private final Customer customer;

    private final Date orderDate;

    private OrderStatus status;

    protected ShippingOrder(List<OrderLine> productList, Seller seller, Customer customer, Date orderDate){
        this.orderLines =productList;
        this.seller=seller;
        this.customer=customer;
        this.orderDate=orderDate;
        this.status=OrderStatus.Placed;
    }

    public void calculateTotal(){
        Double total = 0.0;
        for(OrderLine item : this.orderLines){
            total+=item.getSubtotal();
        }
        this.totalPrice=total;
    }

    public void updateStatus( OrderStatus status){
        this.status=status;
    }

}
