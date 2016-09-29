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
import java.util.Set;

/**
 * Created by khka on 9/29/2016.
 */
@Entity
@Getter
@NoArgsConstructor(force=true)
@AllArgsConstructor
public class ProductsOrder extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private final List<OrderLine> itemSet;
    private final Double totalPrice;
    @OneToOne(cascade = CascadeType.ALL)
    private final Seller seller;
    @OneToOne(cascade = CascadeType.ALL)
    private final Customer customer;
    private final Date orderDate;

}
