package org.project.narcoticsnexus.service;

import lombok.RequiredArgsConstructor;
import org.project.narcoticsnexus.entity.Customer;
import org.project.narcoticsnexus.repo.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }
    public Customer getCustomerByUsername(String username){
        return customerRepository.findById(username).orElse(null);
    }
    public void updateCustomer(Customer customer,String username){
        customer.setUsername(username);
        customerRepository.save(customer);
    }
    public void deleteCustomer(String username){
        customerRepository.deleteById(username);
    }
}
