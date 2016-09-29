package com.x.order.order;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.x.order.order.CustomerFixture.customer1;
import static com.x.order.order.OrderLineFixture.orderLine1;
import static com.x.order.order.OrderLineFixture.orderLine2;
import static com.x.order.order.OrderLineFixture.orderLine3;
import static com.x.order.order.SellerFixture.seller1;

/**
 * Created by khka on 9/29/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void persistsOrder(){
        ProductsOrder testOrder = saveOrder();
        Assert.assertNotNull(testOrder);
        Assert.assertNotNull(testOrder.getId());
        Assert.assertEquals("Seller1",testOrder.getSeller().getFirstName());

    }

    private ProductsOrder saveOrder() {
        List<OrderLine> orderLineList = new ArrayList<OrderLine>();
        orderLineList.add(orderLine1());
        orderLineList.add(orderLine2());
        orderLineList.add(orderLine3());
        ProductsOrder testOrder = new ProductsOrder(orderLineList, 432.78, seller1(), customer1(), new Date());
        testOrder = orderRepository.save(testOrder);
        return testOrder;
    }
}
