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
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void persistsOrder() {
        ProductsOrder testOrder = saveOrder();
        Assert.assertNotNull(testOrder);
        Assert.assertNotNull(testOrder.getId());
        Assert.assertNotNull(testOrder.getCustomer());
        Assert.assertEquals("Customer1", testOrder.getCustomer().getFirstName());
        Assert.assertEquals("CustomerSurName", testOrder.getCustomer().getLastName());
        Assert.assertEquals("test1@gmai.com", testOrder.getCustomer().getEmail());
        Assert.assertNotNull(testOrder.getSeller());
        Assert.assertEquals("Seller1", testOrder.getSeller().getFirstName());
        Assert.assertEquals("SellerLastName", testOrder.getSeller().getLastName());
        Assert.assertEquals("seller@gmail.com", testOrder.getSeller().getEmail());
        Assert.assertEquals("1234UY6",testOrder.getSeller().getTAN());
        Assert.assertNotNull(testOrder.getOrderDate());
        Calendar c =Calendar.getInstance();
        c.set(2016,9,28);
        Assert.assertEquals(c.get(Calendar.MONTH),testOrder.getOrderDate().getMonth());
        Assert.assertNotNull(testOrder.getTotalPrice());
        Assert.assertEquals(166389,testOrder.getTotalPrice().doubleValue(),4);
        Assert.assertNotNull(testOrder.getProductList());
        Assert.assertEquals(product1(), testOrder.getProductList().get(0).getProduct());
        Assert.assertNotNull(testOrder.getStatus());
        Assert.assertEquals(OrderStatus.Placed,testOrder.getStatus());
    }
    @Test
    public void persistsOrderLine() {
        ProductsOrder testOrder = saveOrder();
        Assert.assertNotNull(testOrder.getProductList().get(0));
        Assert.assertNotNull(testOrder.getProductList().get(0).getId());
        Assert.assertNotNull(testOrder.getProductList().get(0).getQuantity());
        Assert.assertNotNull(testOrder.getProductList().get(0).getUnitPrice());
        Assert.assertNotNull(testOrder.getProductList().get(0).getSubtotal());
        Assert.assertEquals(product1(), testOrder.getProductList().get(0).getProduct());
        Assert.assertEquals(3, testOrder.getProductList().get(0).getQuantity().intValue());
        Assert.assertEquals(346.50, testOrder.getProductList().get(1).getUnitPrice().doubleValue(), 3);
        Assert.assertEquals(693, testOrder.getProductList().get(1).getSubtotal().doubleValue(), 2);

    }

    private ProductsOrder saveOrder() {
        List<OrderLine> orderLineList = new ArrayList<OrderLine>();
        orderLineList.add(orderLine1());
        orderLineList.add(orderLine2());
        orderLineList.add(orderLine3());
        Calendar c = Calendar.getInstance();
        c.set(2016,9,28);
        ProductsOrder testOrder = new ProductsOrder(orderLineList, seller1(), customer1(), c.getTime());
        testOrder.calculateTotal();
        testOrder = orderRepository.save(testOrder);
        return testOrder;
    }
}
