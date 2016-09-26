package com.x.order;

import lombok.Getter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author firoz
 * @since 26/09/16
 */
@Getter
@MappedSuperclass
public class AbstractEntity implements Identifiable<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
