package org.project.narcoticsnexus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vendor {
    @Id
    private String username;
    private String licenseId;
    private String panCardId;
    private String address;
    private String companyName;
    private String phoneNumber;
    private String emailId;
    private String city;
    @OneToOne
    private Login login;
}
