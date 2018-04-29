package com.dzieniu2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "transaction_product")
@Data
@EqualsAndHashCode(exclude = {"employee", "customer", "products"})
@ToString(exclude = {"employee", "customer", "products"})
public class TransactionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @PrePersist
    private void setTransactionDate() {
        this.transactionDate = new Date();
    }
}
