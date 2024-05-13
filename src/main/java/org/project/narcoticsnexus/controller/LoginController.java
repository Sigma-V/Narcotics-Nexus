package org.project.narcoticsnexus.controller;

import lombok.RequiredArgsConstructor;
import org.project.narcoticsnexus.eNum.UserType;
import org.project.narcoticsnexus.entity.Login;
import org.project.narcoticsnexus.service.LoginService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @RequestMapping(method = RequestMethod.POST, value = "/user/customer")
    public void addCustomerLogin(@RequestBody Login login){
        login.setUserType(UserType.CUSTOMER);
        loginService.addUser(login);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/user/vendor")
    public void addVendorLogin(@RequestBody Login login){
        login.setUserType(UserType.VENDOR);
        loginService.addUser(login);
    }
    @RequestMapping(method = RequestMethod.PUT,value = "/login/customer")
    public void updateCustomerPassword(@RequestBody Login login){
        login.setUserType(UserType.CUSTOMER);
        loginService.updatePassword(login);
    }
    @RequestMapping(method = RequestMethod.PUT,value = "/login/vendor")
    public void updateVendorPassword(@RequestBody Login login){
        login.setUserType(UserType.VENDOR);
        loginService.updatePassword(login);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/login/{username}")
    public Optional<Login> getLoginDetails(@PathVariable String username){
        return loginService.getLoginInfo(username);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{username}")
    public void deleteUser(@PathVariable String username){
        loginService.deleteUser(username);
    }
}
