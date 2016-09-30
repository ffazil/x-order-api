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
import static com.x.order.order.ProductFixture.product1;
import static com.x.order.order.SellerFixture.seller1;

/**
 * Created by khka on 9/29/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ShippingOrderRepositoryTest {
    @Autowired
    private ShippingOrderRepository shippingOrderRepository;

    @Test
    public void persistsOrder() {
        ShippingOrder testShippingOrder = saveOrder();
        Assert.assertNotNull(testShippingOrder);
        Assert.assertNotNull(testShippingOrder.getId());
        Assert.assertNotNull(testShippingOrder.getCustomer());
        Assert.assertEquals("Customer1", testShippingOrder.getCustomer().getFirstName());
        Assert.assertEquals("CustomerSurName", testShippingOrder.getCustomer().getLastName());
        Assert.assertEquals("test1@gmai.com", testShippingOrder.getCustomer().getEmail());
        Assert.assertNotNull(testShippingOrder.getSeller());
        Assert.assertEquals("Seller1", testShippingOrder.getSeller().getFirstName());
        Assert.assertEquals("SellerLastName", testShippingOrder.getSeller().getLastName());
        Assert.assertEquals("seller@gmail.com", testShippingOrder.getSeller().getEmail());
        Assert.assertEquals("1234UY6", testShippingOrder.getSeller().getTAN());
        Assert.assertNotNull(testShippingOrder.getOrderDate());
        Calendar c =Calendar.getInstance();
        c.set(2016,9,28);
        Assert.assertEquals(c.get(Calendar.MONTH), testShippingOrder.getOrderDate().getMonth());
        Assert.assertNotNull(testShippingOrder.getTotalPrice());
        Assert.assertEquals(166389, testShippingOrder.getTotalPrice().doubleValue(),4);
        Assert.assertNotNull(testShippingOrder.getOrderLines());
        Assert.assertEquals(product1(), testShippingOrder.getOrderLines().get(0).getProduct());
        Assert.assertNotNull(testShippingOrder.getStatus());
        Assert.assertEquals(OrderStatus.Placed, testShippingOrder.getStatus());
    }
    @Test
    public void persistsOrderLine() {
        ShippingOrder testShippingOrder = saveOrder();
        Assert.assertNotNull(testShippingOrder.getOrderLines().get(0));
        Assert.assertNotNull(testShippingOrder.getOrderLines().get(0).getId());
        Assert.assertNotNull(testShippingOrder.getOrderLines().get(0).getQuantity());
        Assert.assertNotNull(testShippingOrder.getOrderLines().get(0).getUnitPrice());
        Assert.assertNotNull(testShippingOrder.getOrderLines().get(0).getSubtotal());
        Assert.assertEquals(product1(), testShippingOrder.getOrderLines().get(0).getProduct());
        Assert.assertEquals(3, testShippingOrder.getOrderLines().get(0).getQuantity().intValue());
        Assert.assertEquals(346.50, testShippingOrder.getOrderLines().get(1).getUnitPrice().doubleValue(), 3);
        Assert.assertEquals(693, testShippingOrder.getOrderLines().get(1).getSubtotal().doubleValue(), 2);

    }

    private ShippingOrder saveOrder() {
        List<OrderLine> orderLineList = new ArrayList<OrderLine>();
        orderLineList.add(orderLine1());
        orderLineList.add(orderLine2());
        orderLineList.add(orderLine3());
        Calendar c = Calendar.getInstance();
        c.set(2016,9,28);
        ShippingOrder testShippingOrder = new ShippingOrder(orderLineList, seller1(), customer1(), c.getTime());
        testShippingOrder.calculateTotal();
        testShippingOrder = shippingOrderRepository.save(testShippingOrder);
        return testShippingOrder;
    }
}
