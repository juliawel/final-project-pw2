package com.ecommerce.demo.services;

import com.ecommerce.demo.domain.Order;
import com.ecommerce.demo.domain.OrderItem;
import com.ecommerce.demo.domain.Payment;
import com.ecommerce.demo.domain.Product;
import com.ecommerce.demo.domain.User;
import com.ecommerce.demo.domain.enums.EOrderStatus;
import com.ecommerce.demo.dtos.OrderDTO;
import com.ecommerce.demo.dtos.OrderItemMinDTO;
import com.ecommerce.demo.dtos.ResponseMessage;
import com.ecommerce.demo.dtos.UserDTO;
import com.ecommerce.demo.repositories.OrderItemRepository;
import com.ecommerce.demo.repositories.OrderRepository;
import com.ecommerce.demo.repositories.PaymentRepository;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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

    @Autowired
    private static PaymentRepository paymentRepository;

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
    public OrderDTO insert(OrderDTO dto, UserDTO user) {
        var order = new Order();
        var client = new User();
        order.setOrdedAt(Instant.now());
        order.setStatus(EOrderStatus.WAITING_PAYMENT);
        copyDtotoEntity(client, user);
        order.setClient(client);

        for (OrderItemMinDTO itemDto : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            var item = new OrderItem(order, product, itemDto.getQuantity());
            order.getItems().add(item);
        }

        confirmPayment(order);

        itemRepository.saveAll(order.getItems());
        repository.save(order);

        return new OrderDTO(order);
    }

    private void confirmPayment(Order order) {
        
        try{
            boolean confirmedPayment = true;
            Thread.sleep(5000);
            if(confirmedPayment){
                var payment = new Payment();
                payment.setPayedAt(Instant.now());
                payment.setOrder(order);
                paymentRepository.save(payment);
                order.setPayment(payment);
                order.setStatus(EOrderStatus.PAID);
        }
    } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public ResponseMessage cancelOrder(String id) {

        try{
            var order = repository.getReferenceById(id);
        
            if (order.getStatus() == EOrderStatus.CANCELED) {
                return new ResponseMessage(400, "Order already canceled");
            }

            order.setStatus(EOrderStatus.CANCELED);
            repository.save(order);
            return new ResponseMessage(200, "Order cancceled");
            
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Order not found");
        }
    }
    
    private void copyDtotoEntity(User user, UserDTO dto) {
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setBirthDate(dto.getBirthDate());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
    }
}
