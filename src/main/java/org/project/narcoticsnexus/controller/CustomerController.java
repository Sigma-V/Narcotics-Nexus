package org.project.narcoticsnexus.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.narcoticsnexus.entity.Cart;
import org.project.narcoticsnexus.entity.Customer;
import org.project.narcoticsnexus.entity.OrderDetails;
import org.project.narcoticsnexus.entity.Wallet;
import org.project.narcoticsnexus.exception.InsufficientFundException;
import org.project.narcoticsnexus.exception.InsufficientStockException;
import org.project.narcoticsnexus.model.Message;
import org.project.narcoticsnexus.service.CartService;
import org.project.narcoticsnexus.service.CustomerService;
import org.project.narcoticsnexus.service.OrderService;
import org.project.narcoticsnexus.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CartService cartService;
    private final OrderService orderService;
    private final WalletService walletService;
    @RequestMapping(method = RequestMethod.PUT, value = "/user/customer/{username}")
    public void updateCustomerDetails(@RequestBody Customer customer, @PathVariable String username){
        customerService.updateCustomer(customer,username);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/user/customer/{username}")
    public Customer getCustomerDetails(@PathVariable String username){
        return customerService.getCustomerByUsername(username);
    }

    //Mappings to cart sctions
    @RequestMapping(method = RequestMethod.POST, value = "/user/customer/{username}/cart/product/{productId}/quantity/{quantity}")
    public void addProductToCart(@PathVariable String username, @PathVariable String productId, @PathVariable String quantity){
        cartService.addCartItem(username, productId, Integer.parseInt(quantity));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/user/customer/{username}/cart")
    public ResponseEntity<List<Cart>> getAllCartItems(@PathVariable String username){
        return ResponseEntity.ok(cartService.getAllCartItemsByCustomer(username));
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/user/customer/{username}/cart/product/{productId}")
    public void updateCartItem(@RequestBody Cart cartItem, @PathVariable String username, @PathVariable String productId){
        cartService.updateCartItem(cartItem, username, productId);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/customer/{username}/cart/item/{itemId}")
    public void deleteCartItem(@PathVariable String username, @PathVariable String itemId){
        cartService.deleteCartItem(Long.parseLong(itemId));
    }

    //Mappings to product buying
    @RequestMapping(method = RequestMethod.POST, value = "/user/customer/{username}/order/product/{productId}/quantity/{quantity}")
    public Message addOrder(@PathVariable String username, @PathVariable String productId, @PathVariable String quantity){
        try {
            orderService.addOrder(username, Long.parseLong(productId), Integer.parseInt(quantity));
            return Message.builder()
                    .message("OrderSuccessful")
                    .build();
        }
        catch (InsufficientStockException e) {
            return Message.builder()
                    .message("OUT OF STOCK!!!!")
                    .build();
        }
        catch (InsufficientFundException e){
            log.info("Fund exception thrown");
            return Message.builder()
                    .message("Insufficient Funds")
                    .build();
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/user/customer/{username}/orders")
    public ResponseEntity<List<OrderDetails>> getAllOrders(@PathVariable String username){
        return ResponseEntity.ok(orderService.getAllOrdersByCustomer(username));
    }

    //Mapping to buy whole cart
    @RequestMapping(method = RequestMethod.POST, value = "/user/customer/{username}/order/cart")
    public Message orderCart(@PathVariable String username){
        try {
            orderService.addCartOrder(username);
            return Message.builder()
                    .message("OrderSuccessful")
                    .build();
        } catch (InsufficientFundException e) {
            return Message.builder()
                    .message("Insufficient Funds")
                    .build();
        } catch (InsufficientStockException e) {
            return Message.builder()
                    .message("OUT OF STOCK!!!!")
                    .build();
        }
    }

    //Mappings to Wallet
    @RequestMapping(method=RequestMethod.GET, value="/user/customer/{username}/wallet")
    public Wallet getWallet(@PathVariable String username){
        return walletService.getWalletByCustomer(username);
    }
    @RequestMapping(method=RequestMethod.POST, value = "/user/customer/{username}/wallet")
    public void addWallet(@PathVariable String username){
        walletService.addWallet(username,0);
    }
    @RequestMapping(method=RequestMethod.PUT, value = "/user/customer/{username}/wallet/{id}/amount/{amount}")
    public void addBalance(@PathVariable String username,@PathVariable String id,@PathVariable String amount){
        walletService.updateBalance(Long.parseLong(id),Long.parseLong(amount));
    }

}
