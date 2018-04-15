package com.dzieniu2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "product")
@Data
@EqualsAndHashCode(exclude = "transactions")
@ToString(exclude = "transactions")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer remaining;
    private ProductCategory category;
    private String path;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private Set<Transaction> transactions = new HashSet(0);

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getPath() {
        return path;
    }
}
