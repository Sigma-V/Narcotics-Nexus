package org.project.narcoticsnexus.service;


import lombok.RequiredArgsConstructor;
import org.project.narcoticsnexus.entity.Cart;
import org.project.narcoticsnexus.entity.Customer;
import org.project.narcoticsnexus.entity.Product;
import org.project.narcoticsnexus.repo.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CustomerService customerService;
    public void addCartItem(String username, String productId, int quantity){
        Customer customer = Customer.builder()
                .username(username)
                .build();
        Product product = Product.builder()
                .productId(Long.parseLong(productId))
                .build();
        Cart cartItem = Cart.builder()
                .customer(customer)
                .product(product)
                .quantity(quantity)
                .build();
        cartRepository.save(cartItem);
    }
    public List<Cart> getAllCartItemsByCustomer(String username){
        Customer customer = customerService.getCustomerByUsername(username);
        return new ArrayList<>(cartRepository.findAllByCustomer(customer));
    }
    public void deleteCartItem(Long cartId){
        cartRepository.deleteById(cartId);
    }
    public void updateCartItem(Cart cartItem, String username, String productId){
        cartItem.setCustomer(Customer.builder().username(username).build());
        cartItem.setProduct(Product.builder().productId(Long.parseLong(productId)).build());
        cartRepository.save(cartItem);
    }
}
