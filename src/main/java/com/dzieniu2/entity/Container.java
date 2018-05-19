package com.dzieniu2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
