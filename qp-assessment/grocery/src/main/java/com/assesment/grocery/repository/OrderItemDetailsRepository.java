package com.assesment.grocery.repository;

import com.assesment.grocery.entity.OrderItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDetailsRepository extends JpaRepository<OrderItemDetails, Long> {
}
