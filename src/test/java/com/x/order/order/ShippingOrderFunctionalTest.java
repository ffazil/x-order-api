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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.x.order.order.CustomerFixture.customer1;
import static com.x.order.order.OrderLineFixture.orderLine;
import static com.x.order.order.ProductFixture.product1;
import static com.x.order.order.ProductFixture.product2;
import static com.x.order.order.ProductFixture.product3;
import static com.x.order.order.SellerFixture.seller1;

/**
 * Created by khka on 9/30/2016.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ShippingOrderFunctionalTest {

    @Autowired
    ShippingOrderRepository shippingOrderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Test
    public void isCorrectTotalBalanceCalculatedForOrder(){
        ShippingOrder testShippingOrder = getProductsOrder();
        testShippingOrder.calculateTotal();
        testShippingOrder = shippingOrderRepository.save(testShippingOrder);
        Assert.assertEquals(166389, testShippingOrder.getTotalPrice().doubleValue(),4);
    }

    @Test
    public void updateOrderStatus() throws ClassNotFoundException,NoSuchFieldException,IllegalAccessException{
        ShippingOrder testShippingOrder = getProductsOrder();
        Field orderStatus = testShippingOrder.getClass().getDeclaredField("status");
        orderStatus.setAccessible(true);
        orderStatus.set(testShippingOrder,OrderStatus.Payed);
        testShippingOrder = shippingOrderRepository.save(testShippingOrder);
        Assert.assertEquals(OrderStatus.Payed, testShippingOrder.getStatus());
    }

    private ShippingOrder getProductsOrder() {
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
