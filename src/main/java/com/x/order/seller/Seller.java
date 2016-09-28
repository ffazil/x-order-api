package com.x.order.seller;

import com.x.order.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author ismu
 * @since 9/26/2016.
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Seller extends AbstractEntity {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String TAN;

}
