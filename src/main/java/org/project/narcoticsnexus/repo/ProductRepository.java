package org.project.narcoticsnexus.repo;

import org.project.narcoticsnexus.entity.Product;
import org.project.narcoticsnexus.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByProductName(String name);
    List<Product> findAllByVendor(Vendor vendor);
    List<Product> findAllByCategory(String category);
}
