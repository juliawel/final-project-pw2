package com.ecommerce.demo.domain;

import jakarta.persistence.*;
import main.java.com.ecommerce.demo.domain.enums.EOrderStatus;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
    private Instant ordedAt;

    private main.java.com.ecommerce.demo.domain.enums.EOrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items  = new HashSet<>();

    public Order(String id, Instant ordedAt, User user, Payment payment, Set<OrderItem> items, main.java.com.ecommerce.demo.domain.enums.EOrderStatus status) {
        this.id = id;
        this.ordedAt = ordedAt;
        this.user = user;
        this.payment = payment;
        this.items = items;
        this.status = status;
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

    public List<Product> getProducts(){
        return items.stream().map(x -> x.getProduct()).toList();
    }

    public EOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EOrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
