package com.x.order.product;

import com.x.order.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by masu on 26-Sep-16.
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Product extends AbstractEntity {

    private final String name;
    private final String description;
}