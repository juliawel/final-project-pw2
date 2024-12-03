package com.ecommerce.demo.dtos;

import com.ecommerce.demo.domain.Order;
import com.ecommerce.demo.domain.enums.EOrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private String id;
    private Instant orderdAt;
    private EOrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;
    private List<OrderItemMinDTO> items = new ArrayList<>();

    public OrderDTO(String id,
                    Instant createdAt,
                    EOrderStatus status,
                    ClientDTO client,
                    PaymentDTO payment,
                    List<OrderItemMinDTO> items) {
        this.id = id;
        this.orderdAt = createdAt;
        this.status = status;
        this.client = client;
        this.payment = payment;
        this.items = items;
    }

    public OrderDTO(Order entity){
        id = entity.getId();
        orderdAt = entity.getOrdedAt();
        status = entity.getStatus();
        client = new ClientDTO(entity.getClient());
        payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
        entity.getItems().forEach(item -> this.items.add(new OrderItemMinDTO(item)));
    }

    public String getId() {
        return id;
    }

    public Instant getOrderdAt() {
        return orderdAt;
    }

    public EOrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemMinDTO> getItems() {
        return items;
    }
}
