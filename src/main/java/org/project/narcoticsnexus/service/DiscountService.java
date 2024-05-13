package org.project.narcoticsnexus.service;

import lombok.RequiredArgsConstructor;
import org.project.narcoticsnexus.entity.Discount;
import org.project.narcoticsnexus.repo.DiscountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;
    public void addDiscount(Discount discount){
        discountRepository.save(discount);
    }
    public Discount getDiscount(String code){
        return discountRepository.findById(code).orElse(new Discount());
    }
    public List<Discount> getAllDiscounts(){
        return new ArrayList<>(discountRepository.findAll());
    }
    public void removeDiscount(String code){
        discountRepository.deleteById(code);
    }
}
