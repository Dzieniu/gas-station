package com.dzieniu2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "container")
@Data
@EqualsAndHashCode(exclude = "fuel")
@ToString(exclude = "fuel")
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fuel_left")
    private Double fuelLeft;

    @Column(name = "max_capacity")
    private Long maxCapacity;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "container")
    private Fuel fuel;

}
