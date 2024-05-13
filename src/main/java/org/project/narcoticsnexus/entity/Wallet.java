package org.project.narcoticsnexus.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float balance;
    private LocalDate dateOfCreation;
    @OneToOne
    private Customer customer;

}
