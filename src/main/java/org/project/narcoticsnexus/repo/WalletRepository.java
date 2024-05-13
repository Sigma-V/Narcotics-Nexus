package org.project.narcoticsnexus.repo;

import org.project.narcoticsnexus.entity.Customer;
import org.project.narcoticsnexus.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Wallet findByCustomer(Customer customer);
}
