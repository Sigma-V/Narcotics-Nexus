package org.project.narcoticsnexus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.project.narcoticsnexus.eNum.UserType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Login {
    @Id
    private String username;
    private String pass;
    private UserType userType;
    private boolean isBlocked=false;
}

