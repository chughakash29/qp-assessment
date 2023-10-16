package com.assesment.grocery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Grocery")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Grocery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GrocerySequenceId")
    @SequenceGenerator(name = "GrocerySequenceId", sequenceName = "grocery_seq", allocationSize = 1)
    @EqualsAndHashCode.Exclude
    private Long groceryId;
    private String groceryName;
    @EqualsAndHashCode.Exclude
    private Double price;
    @OneToOne(mappedBy = "grocery", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private ItemQuantity itemQuantity;
}
