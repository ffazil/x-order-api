package com.x.order.customer;

import com.x.order.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;

/**
 * @author firoz
 * @since 26/09/16
 */
@Entity
@Getter
@AllArgsConstructor
public class Customer extends AbstractEntity{

    private final String firstName;
    private final String lastName;
    private final String email;

    protected Customer(){
        this(null, null, null);
    }


}
