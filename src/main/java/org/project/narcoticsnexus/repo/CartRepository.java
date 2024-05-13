package org.project.narcoticsnexus.repo;

import org.project.narcoticsnexus.entity.Cart;
import org.project.narcoticsnexus.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByCustomer(Customer customer);
}
