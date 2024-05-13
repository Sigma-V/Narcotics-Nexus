package org.project.narcoticsnexus.service;

import lombok.RequiredArgsConstructor;
import org.project.narcoticsnexus.entity.Vendor;
import org.project.narcoticsnexus.repo.VendorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorService {
    private final VendorRepository vendorRepository;
    public void addVendor(Vendor vendor){
        vendorRepository.save(vendor);
    }
    public Vendor getVendorByUsername(String username){
        return vendorRepository.findById(username).orElse(null);
    }
    public void updateVendor(Vendor vendor){
        vendorRepository.save(vendor);
    }
    public void deleteVendor(String username){
        vendorRepository.deleteById(username);
    }
}
