package com.ecommerce.demo.services;

import com.ecommerce.demo.domain.Order;
import com.ecommerce.demo.domain.OrderItem;
import com.ecommerce.demo.domain.Product;
import com.ecommerce.demo.domain.User;
import com.ecommerce.demo.domain.enums.EOrderStatus;
import com.ecommerce.demo.dtos.OrderDTO;
import com.ecommerce.demo.dtos.OrderItemDTO;
import com.ecommerce.demo.repositories.OrderItemRepository;
import com.ecommerce.demo.repositories.OrderRepository;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> getAll(){

        var orders = repository.findAll();

        return orders.stream().map(OrderDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public OrderDTO getById(String id){

        var order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found")
        );

        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){

        var order = new Order();
        order.setOrdedAt(Instant.now());
        order.setStatus(EOrderStatus.WAITING_PAYMENT);
        User user = new User();
        order.setClient(user);

        for(OrderItemDTO itemDto : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            var item = new OrderItem(order, product, itemDto.getQuantity(), itemDto.getPrice());
            order.getItems().add(item);
        }

        itemRepository.saveAll(order.getItems());
        repository.save(order);

        return new OrderDTO(order);
    }

    @Transactional
    public void cancelOrder(String id){

        var order = repository.getReferenceById(id);
        order.setStatus(EOrderStatus.CANCELED);
        repository.save(order);
    }
}
