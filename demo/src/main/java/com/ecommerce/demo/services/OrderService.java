package com.ecommerce.demo.services;

import com.ecommerce.demo.domain.Order;
import com.ecommerce.demo.domain.OrderItem;
import com.ecommerce.demo.domain.Payment;
import com.ecommerce.demo.domain.Product;
import com.ecommerce.demo.domain.User;
import com.ecommerce.demo.domain.enums.EOrderStatus;
import com.ecommerce.demo.dtos.OrderDTO;
import com.ecommerce.demo.dtos.OrderItemDTO;
import com.ecommerce.demo.dtos.OrderItemMinDTO;
import com.ecommerce.demo.repositories.OrderItemRepository;
import com.ecommerce.demo.repositories.OrderRepository;
import com.ecommerce.demo.repositories.PaymentRepository;
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
    private static OrderRepository repository;

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
    public OrderDTO insert(OrderDTO dto) {
        var order = new Order();
        order.setOrdedAt(Instant.now());
        order.setStatus(EOrderStatus.WAITING_PAYMENT);
        User user = new User();
        order.setClient(user);

        for (OrderItemMinDTO itemDto : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            var item = new OrderItem(order, product, itemDto.getQuantity());
            order.getItems().add(item);
        }

        itemRepository.saveAll(order.getItems());
        repository.save(order);

        Thread paymenThread = new Thread(confirmPayment(order));
        paymenThread.start();

        return new OrderDTO(order);
    }

    private static Runnable confirmPayment(Order order) {
        return () -> {
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
                    repository.save(order);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        };
    }

    @Transactional
    public void cancelOrder(String id){

        var order = repository.getReferenceById(id);
        order.setStatus(EOrderStatus.CANCELED);
        repository.save(order);
    }
}
