package org.project.narcoticsnexus.entity;

import jakarta.persistence.*;
import lombok.*;
import org.project.narcoticsnexus.eNum.RepeatFrequency;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subscriptionId;
    @ManyToOne
    private Product product;
    @ManyToOne
    private  Customer customer;
    private int quantity;
    private RepeatFrequency repeatFrequency;
}
