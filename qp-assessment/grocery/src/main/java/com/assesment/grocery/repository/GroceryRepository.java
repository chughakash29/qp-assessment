package com.assesment.grocery.repository;

import com.assesment.grocery.entity.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Long> {

    @Query(value = "select g from Grocery g where itemQuantity.quantity>0")
    List<Grocery> getGroceryDetails();

    @Query(value="select g from Grocery g where groceryName in :groceries")
    List<Grocery> getGroceryByName(List<String> groceries);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Grocery> findById(Long groceryId);

}
