package com.assesment.grocery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="order_item_details")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrderItemSequenceId")
    @SequenceGenerator(name = "OrderItemSequenceId", sequenceName = "order_item_seq", allocationSize = 1)
    private Long id;
    private Long groceryId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private OrderDetails orderDetails;
    private Long quantity;
}

