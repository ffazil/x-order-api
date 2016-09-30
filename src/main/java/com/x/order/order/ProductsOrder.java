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
public class ProductsOrder extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private final List<OrderLine> productList;
    private Double totalPrice;
    @OneToOne(cascade = CascadeType.ALL)
    private final Seller seller;
    @OneToOne(cascade = CascadeType.ALL)
    private final Customer customer;
    private final Date orderDate;
    private OrderStatus status;

    protected ProductsOrder(List<OrderLine> productList,Seller seller,Customer customer,Date orderDate){
        this.productList=productList;
        this.seller=seller;
        this.customer=customer;
        this.orderDate=orderDate;
        this.status=OrderStatus.Placed;
    }

    public void calculateTotal(){
        Double total = 0.0;
        for(OrderLine item : this.productList){
            total+=item.getSubtotal();
        }
        this.totalPrice=total;
    }

    public void updateStatus( OrderStatus status){
        this.status=status;
    }

}
