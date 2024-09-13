package com.ecommerce.demo.domain;

import jakarta.persistence.*;

import java.time.Instant;

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
    private Instant payedAt;

    @OneToOne
    @MapsId
    private Order order;
}
