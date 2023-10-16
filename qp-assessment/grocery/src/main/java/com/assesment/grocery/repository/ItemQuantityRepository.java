package com.assesment.grocery.repository;

import com.assesment.grocery.entity.Grocery;
import com.assesment.grocery.entity.ItemQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ItemQuantityRepository extends JpaRepository<ItemQuantity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ItemQuantity> findById(Long groceryId);
}
