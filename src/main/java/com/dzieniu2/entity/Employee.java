package com.dzieniu2.entity;

import com.dzieniu2.entity.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"transactionFuel", "transactionProduct"})
@ToString(exclude = {"transactionFuel", "transactionProduct"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true)
    private String login;
    private String password;
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<TransactionFuel> transactionFuel = new HashSet(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<TransactionProduct> transactionProduct = new HashSet(0);
}
