package com.x.order.order;

import com.x.order.customer.Customer;
import com.x.order.customer.CustomerRepository;
import com.x.order.product.Product;
import com.x.order.product.ProductRepository;
import com.x.order.seller.Seller;
import com.x.order.seller.SellerRepository;
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
import static com.x.order.order.OrderLineFixture.*;
import static com.x.order.order.ProductFixture.product1;
import static com.x.order.order.ProductFixture.product2;
import static com.x.order.order.ProductFixture.product3;
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
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SellerRepository sellerRepository;

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
        Product p1 = product1();
        Product p2 = product2();
        Product p3 = product3();

        p1 = productRepository.save(p1);
        p2 = productRepository.save(p2);
        p3 = productRepository.save(p3);

        Customer c1 = customer1();
        c1 = customerRepository.save(c1);

        Seller s1 = seller1();
        s1 = sellerRepository.save(s1);


        List<OrderLine> orderLineList = new ArrayList<OrderLine>();
        orderLineList.add(orderLine(p1,3));
        orderLineList.add(orderLine(p2,2));
        orderLineList.add(orderLine(p3,5));
        Calendar c = Calendar.getInstance();
        c.set(2016,9,28);

        ShippingOrder testShippingOrder = new ShippingOrder(orderLineList, s1, c1, c.getTime());
        testShippingOrder.calculateTotal();

        testShippingOrder = shippingOrderRepository.save(testShippingOrder);
        return testShippingOrder;
    }
}
