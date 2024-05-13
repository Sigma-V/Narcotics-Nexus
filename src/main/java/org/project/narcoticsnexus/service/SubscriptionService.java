package org.project.narcoticsnexus.service;

import lombok.RequiredArgsConstructor;
import org.project.narcoticsnexus.entity.Customer;
import org.project.narcoticsnexus.entity.Subscription;
import org.project.narcoticsnexus.repo.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    public void addSubscription(Subscription subscription){
        subscriptionRepository.save(subscription);
    }
    public Subscription getSubscriptionById(long id){
        return subscriptionRepository.findById(id).orElse(new Subscription());
    }
    public List<Subscription> getSubscriptionsByCustomer(Customer customer){
        return new ArrayList<>(subscriptionRepository.findAllByCustomer(customer));
    }
    public void removeSubscription(long id){
        subscriptionRepository.deleteById(id);
    }
    public void updateSubscription(Subscription subscription){
        subscriptionRepository.save(subscription);
    }
}
