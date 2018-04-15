package com.dzieniu2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "fuel")
@Data
@EqualsAndHashCode(exclude = {"container", "transactions"})
@ToString(exclude = {"container", "transactions"})
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @OneToOne(fetch = FetchType.LAZY)
    private Container container;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fuel")
    private Set<Transaction> transactions = new HashSet(0);
}
