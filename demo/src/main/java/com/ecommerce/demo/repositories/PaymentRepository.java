package com.ecommerce.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String>{

}
