package org.project.narcoticsnexus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.project.narcoticsnexus.entity.Customer;
import org.project.narcoticsnexus.entity.Wallet;
import org.project.narcoticsnexus.repo.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final CustomerService customerService;

    public Wallet getWalletByCustomer(String username){
        Customer customer = customerService.getCustomerByUsername(username);
        Wallet wallet = walletRepository.findByCustomer(customer);
        return Objects.requireNonNullElseGet(wallet, Wallet::new);
    }
    public Wallet getWallet(long walletId){
        return walletRepository.findById(walletId).orElse(new Wallet());
    }
    public void addWallet(String username, int amount){
        Customer customer= Customer.builder()
                .username(username)
                .build();
        Wallet wallet= Wallet.builder()
                .customer(customer)
                .balance(amount)
                .dateOfCreation(LocalDate.now())
                .build();
        walletRepository.save(wallet);
    }
    @Transactional
    public void updateBalance(long walletId,float amount){
        log.info("Amount added "+amount);
        Wallet wallet=getWallet(walletId);
        wallet.setBalance(wallet.getBalance()+amount);
        walletRepository.save(wallet);
    }
}
