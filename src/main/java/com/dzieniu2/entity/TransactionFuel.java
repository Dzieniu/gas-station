package com.dzieniu2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "transaction_fuel")
@Data
@EqualsAndHashCode(exclude = {"employee", "customer", "fuel"})
@ToString(exclude = {"employee", "customer", "fuel"})
public class TransactionFuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "fuel_quantity")
    private Double fuelQuantity;

    @Column(name = "fuel_price")
    private Double fuelPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_id", nullable = false)
    private Fuel fuel;

//    @ManyToMany
//    @JoinTable(name = "transaction_product", joinColumns = {
//            @JoinColumn(name = "transaction_id")},
//            inverseJoinColumns = {@JoinColumn(name = "product_id")})
//    private Set<Product> products = new HashSet(0);

    @PrePersist
    private void setTransactionDate() {
        this.transactionDate = new Date();
    }

}
