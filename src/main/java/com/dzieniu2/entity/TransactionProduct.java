package com.dzieniu2.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TransactionProduct {

    @Id
    private Long id;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "products_price")
    private Double productsPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToMany
    @JoinTable(name = "transactions_products", joinColumns = {
            @JoinColumn(name = "transaction_product_id")},
            inverseJoinColumns = {@JoinColumn(name ="product_id")})
    private Set<Product> products = new HashSet(0);
}
