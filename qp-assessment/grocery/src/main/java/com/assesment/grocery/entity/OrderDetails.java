package com.assesment.grocery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="order_details")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrderSequenceId")
    @SequenceGenerator(name = "OrderSequenceId", sequenceName = "order_seq", allocationSize = 1)
    @Column(name="order_id")
    private Long orderId;
    private Date orderPlaced;
    private Long userId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private List<OrderItemDetails> orderItemDetailsList = new ArrayList<>();

}
