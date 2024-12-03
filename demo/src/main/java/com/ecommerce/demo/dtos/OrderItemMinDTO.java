package com.ecommerce.demo.dtos;

import com.ecommerce.demo.domain.OrderItem;

public class OrderItemMinDTO {
    private Long productId;
    private String name;
    private Integer quantity;
    private String imgUrl;

    public OrderItemMinDTO(Long productId, String name, Integer quantity, String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public OrderItemMinDTO(OrderItem entity){
        productId = entity.getProduct().getId();
        name = entity.getProduct().getName();
        quantity = entity.getQuantity();
        imgUrl = entity.getProduct().getImgURL();
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
