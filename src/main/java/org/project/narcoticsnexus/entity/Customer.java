package org.project.narcoticsnexus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    private String username;
    private boolean membership=false;
    private int nexusPoints=0;
    private String firstName;
    private String lastName;
    private String aadharNum;
    private LocalDate dob;
    private String phoneNum;
    private String emailId;
    private String upiId;
    private String address;
    @OneToOne
    private Login login;

}
