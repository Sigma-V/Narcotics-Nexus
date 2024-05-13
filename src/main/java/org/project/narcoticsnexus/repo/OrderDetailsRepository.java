package org.project.narcoticsnexus.repo;

import org.project.narcoticsnexus.entity.Customer;
import org.project.narcoticsnexus.entity.OrderDetails;
import org.project.narcoticsnexus.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findAllByCustomer(Customer customer);
    List<OrderDetails> findAllByProduct(Product product);
}
