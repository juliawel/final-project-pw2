package com.ecommerce.demo.dtos;

import com.ecommerce.demo.domain.OrderItem;

public class OrderItemMinDTO {
    private Long productId;
    private Integer quantity;
    private String imgUrl;

    public OrderItemMinDTO(Long productId, Integer quantity, String imgUrl) {
        this.productId = productId;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public OrderItemMinDTO(OrderItem entity){
        productId = entity.getProduct().getId();
        quantity = entity.getQuantity();
        imgUrl = entity.getProduct().getImgURL();
    }

    public Long getProductId() {
        return productId;
    }
    
    public Integer getQuantity() {
        return quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
