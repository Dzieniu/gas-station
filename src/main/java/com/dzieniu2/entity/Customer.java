package com.dzieniu2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "customer")
@Data
@EqualsAndHashCode(exclude = "transactions")
@ToString(exclude = "transactions")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "card_code")
    private String cardCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<TransactionFuel> transactionFuels = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<TransactionProduct> transactionProduct = new HashSet(0);

    @PrePersist
    private void setDefaultRegisterDate() {
        this.registerDate = new Date();
    }
}
