package org.project.narcoticsnexus.repo;

import org.project.narcoticsnexus.entity.Customer;
import org.project.narcoticsnexus.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    List<Subscription> findAllByCustomer(Customer customer);
}
