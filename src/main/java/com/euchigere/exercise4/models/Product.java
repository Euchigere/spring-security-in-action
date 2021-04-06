package com.euchigere.exercise4.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
