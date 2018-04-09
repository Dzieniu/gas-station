package com.dzieniu2.entity;

import javafx.beans.property.Property;
import lombok.*;

import javax.persistence.*;

@Entity(name = "container")
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
