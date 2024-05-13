package org.project.narcoticsnexus.service;

import lombok.RequiredArgsConstructor;
import org.project.narcoticsnexus.eNum.UserType;
import org.project.narcoticsnexus.entity.Customer;
import org.project.narcoticsnexus.entity.Login;
import org.project.narcoticsnexus.entity.Vendor;
import org.project.narcoticsnexus.repo.LoginRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;
    private final CustomerService customerService;
    private final VendorService vendorService;
    private final JdbcClient jdbcClient;
    public void addUser(Login login){
        loginRepository.save(login);
        if(login.getUserType()==UserType.CUSTOMER){
            Customer customer = Customer.builder()
                    .username(login.getUsername())
                    .firstName(login.getUsername())
                    .build();
            customerService.addCustomer(customer);
        }
        else if(login.getUserType()==UserType.VENDOR){
            Vendor vendor = Vendor.builder()
                    .username(login.getUsername())
                    .companyName(login.getUsername())
                    .build();
            vendorService.addVendor(vendor);
        }
    }
    public Optional<Login> getLoginInfo(String username){
        String sql="SELECT * FROM login WHERE username = ?";
        Optional<Login> login =jdbcClient.sql(sql).params(username).query(Login.class).optional();
        if(login.isPresent()){
            return login;
        }
        else{
            return Optional.of(new Login());
        }
    }
    public void deleteUser(String username){
        Login loginInfo = loginRepository.findById(username).orElse(null);
        if(loginInfo!=null){
            if(loginInfo.getUserType() == UserType.CUSTOMER){
                customerService.deleteCustomer(username);
            }
            else if (loginInfo.getUserType() == UserType.VENDOR) {
                vendorService.deleteVendor(username);
            }
        }
        loginRepository.deleteById(username);
    }
    public void updatePassword(Login login){
        loginRepository.save(login);
    }
}
