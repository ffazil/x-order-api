package com.x.order.customer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Field;

import static com.x.order.customer.CustomerFixture.*;

/**
 * @author firoz
 * @since 28/09/16
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CustomerRepositoryTest {


    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void savesCustomer(){
        Customer suresh = saveCustomer();
        Assert.assertNotNull(suresh);
        Assert.assertNotNull(suresh.getId());
        Assert.assertEquals("Suresh", suresh.getFirstName());
        Assert.assertEquals("Maurya", suresh.getLastName());
        Assert.assertEquals("suresh.maurya1@gmail.com", suresh.getEmail());
    }

    private Customer saveCustomer() {
        Customer suresh = suresh();
        suresh = customerRepository.save(suresh);
        return suresh;
    }

    @Test
    public void updatesCustomersEmail() throws NoSuchFieldException, IllegalAccessException{
        Customer suresh = saveCustomer();
        Assert.assertEquals("suresh.maurya1@gmail.com", suresh.getEmail());
        Class<?> clazz = suresh.getClass();
        Field field = clazz.getDeclaredField("email");
        field.setAccessible(true);
        field.set(suresh, "test@gmail.com");
        suresh = customerRepository.save(suresh);
        Assert.assertEquals("test@gmail.com", suresh.getEmail());
    }


}
