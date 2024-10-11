package com.ecommerce.demo.dtos;

import com.ecommerce.demo.domain.Payment;

import java.time.Instant;

public class PaymentDTO {

    private String id;
    private Instant payedAt;

    public PaymentDTO(String id, Instant payedAt) {
        this.id = id;
        this.payedAt = payedAt;
    }

    public PaymentDTO(){

    }

    public PaymentDTO(Payment entity){
        id = entity.getId();
        payedAt = entity.getPayedAt();
    }

    public String getId() {
        return id;
    }

    public Instant getPayedAt() {
        return payedAt;
    }
}
