package com.ecommerce.demo.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
    private Instant ordedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items  = new HashSet<>();

    public Order(String id, Instant ordedAt, User user, Payment payment, Set<OrderItem> items) {
        this.id = id;
        this.ordedAt = ordedAt;
        this.user = user;
        this.payment = payment;
        this.items = items;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getOrdedAt() {
        return ordedAt;
    }

    public void setOrdedAt(Instant ordedAt) {
        this.ordedAt = ordedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
}
