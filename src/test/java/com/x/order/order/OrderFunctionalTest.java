package com.x.order.order;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.x.order.order.CustomerFixture.customer1;
import static com.x.order.order.OrderLineFixture.orderLine1;
import static com.x.order.order.OrderLineFixture.orderLine2;
import static com.x.order.order.OrderLineFixture.orderLine3;
import static com.x.order.order.SellerFixture.seller1;

/**
 * Created by khka on 9/30/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class OrderFunctionalTest {

    @Autowired
    OrderRepository orderRepository;
    @Test
    public void isCorrectTotalBalanceCalculatedForOrder(){
        ProductsOrder testOrder = getProductsOrder();
        testOrder.calculateTotal();
        testOrder = orderRepository.save(testOrder);
        Assert.assertEquals(166389,testOrder.getTotalPrice().doubleValue(),4);
    }

    private ProductsOrder getProductsOrder() {
        List<OrderLine> orderLineList = new ArrayList<OrderLine>();
        orderLineList.add(orderLine1());
        orderLineList.add(orderLine2());
        orderLineList.add(orderLine3());
        Calendar c = Calendar.getInstance();
        c.set(2016,9,30);
        return new ProductsOrder(orderLineList, seller1(), customer1(), c.getTime());
    }

    @Test
    public void updateOrderStatus() throws ClassNotFoundException,NoSuchFieldException,IllegalAccessException{
        ProductsOrder testOrder = getProductsOrder();
        Field orderStatus = testOrder.getClass().getDeclaredField("status");
        orderStatus.setAccessible(true);
        orderStatus.set(testOrder,OrderStatus.Payed);
        testOrder = orderRepository.save(testOrder);
        Assert.assertEquals(OrderStatus.Payed,testOrder.getStatus());
    }
}
