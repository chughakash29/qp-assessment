package com.assesment.grocery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name="item_quantity")
public class ItemQuantity implements Serializable {
    @Id
    @Column(name = "grocery_id")
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "grocery_id")
    @JsonBackReference
    private Grocery grocery;
    private Long quantity;
}
