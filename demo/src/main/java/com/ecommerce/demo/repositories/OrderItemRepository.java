package com.ecommerce.demo.repositories;

import com.ecommerce.demo.domain.OrdemItemPK;
import com.ecommerce.demo.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrdemItemPK> {
}
