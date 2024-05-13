package org.project.narcoticsnexus.repo;

import org.project.narcoticsnexus.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
