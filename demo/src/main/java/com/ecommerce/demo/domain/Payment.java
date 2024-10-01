package com.ecommerce.demo.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant payedAt;

    @OneToOne
    @MapsId
    private Order order;

    public Payment(){
    }

    public Payment(String id, Instant payedAt, Order order){
        this.id = id;
        this.payedAt = payedAt;
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getPayedAt() {
        return payedAt;
    }

    public void setPayedAt(Instant payedAt) {
        this.payedAt = payedAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
