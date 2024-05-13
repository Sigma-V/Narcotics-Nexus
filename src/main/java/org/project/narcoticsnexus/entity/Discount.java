package org.project.narcoticsnexus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Discount {
    @Id
    private String discountCode;
    private String category;
    private float discountPercent;
}
