package com.x.order.order;

import com.x.order.customer.Customer;
import com.x.order.customer.CustomerInitializer;
import com.x.order.customer.CustomerRepository;
import com.x.order.product.Product;
import com.x.order.product.ProductInitializer;
import com.x.order.product.ProductRepository;
import com.x.order.seller.Seller;
import com.x.order.seller.SellerInitializer;
import com.x.order.seller.SellerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by khka on 9/30/2016.
 */
@Service
@Slf4j
public class ShippingOrderInitializer {
    private ShippingOrderRepository shippingOrderRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private SellerRepository sellerRepository;

    public ShippingOrderInitializer(ShippingOrderRepository shippingOrderRepository,ProductRepository productRepository,SellerRepository sellerRepository,CustomerRepository customerRepository){
        this.shippingOrderRepository=shippingOrderRepository;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.customerRepository = customerRepository;


        Product product = new Product("Reservation", "Evening Reservation",534.00);
        Product product1 = new Product("laptop", "LaptopDesc",50000.00);

        product = productRepository.save(product);
        product1 = productRepository.save(product1);


        Customer customer = new Customer("Suresh", "Maurya", "suresh.maurya1@gmail.com");
        customer = customerRepository.save(customer);

        Seller seller = new Seller("Gopal", "Nayak", "gopal.nayak@gmail.com","TAEDSD232");
        seller = sellerRepository.save(seller);

        OrderLine o1 =  new OrderLine(product,3);
        o1.calculateSubTotal();
        OrderLine o2 = new OrderLine(product1, 2);
        o2.calculateSubTotal();
        List<OrderLine> orderLines = new ArrayList<OrderLine>();
        orderLines.add(o1);
        orderLines.add(o2);
        ShippingOrder shippingOrder = new ShippingOrder(orderLines,seller,customer,new Date());
        shippingOrder.calculateTotal();
        shippingOrder = shippingOrderRepository.save(shippingOrder);
        log.info("Added Order {}", shippingOrder.getStatus());

    }
}
