package com.ecommerce.demo.domain;

import com.ecommerce.demo.domain.enums.EOrderStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant ordedAt;

    private EOrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items  = new HashSet<>();

    public Order(String id, Instant ordedAt, User client, Payment payment, Set<OrderItem> items, EOrderStatus status) {
        this.id = id;
        this.ordedAt = ordedAt;
        this.client = client;
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

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
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
