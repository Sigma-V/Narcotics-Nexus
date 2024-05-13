package org.project.narcoticsnexus.repo;

import org.project.narcoticsnexus.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount,String> {
}
