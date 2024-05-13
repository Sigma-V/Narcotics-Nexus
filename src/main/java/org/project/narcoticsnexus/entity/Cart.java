package org.project.narcoticsnexus.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;
    private int quantity;
}
