package com.ecommerce.demo.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.demo.domain.Order;
import com.ecommerce.demo.domain.enums.EOrderStatus;

public class OrderMinDTO {

    private String id;
    private Instant orderdAt;
    private EOrderStatus status;
    private List<OrderItemMinDTO> items = new ArrayList<>();

    public OrderMinDTO(String id,
                    Instant createdAt,
                    EOrderStatus status,
                    ClientDTO client,
                    PaymentDTO payment,
                    List<OrderItemMinDTO> items) {
        this.id = id;
        this.orderdAt = createdAt;
        this.status = status;
        this.items = items;
    }

    public OrderMinDTO(Order entity){
        id = entity.getId();
        orderdAt = entity.getOrdedAt();
        status = entity.getStatus();
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

    public List<OrderItemMinDTO> getItems() {
        return items;
    }

}
