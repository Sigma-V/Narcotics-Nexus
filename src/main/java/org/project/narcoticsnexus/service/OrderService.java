package org.project.narcoticsnexus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.narcoticsnexus.entity.*;
import org.project.narcoticsnexus.exception.InsufficientFundException;
import org.project.narcoticsnexus.exception.InsufficientStockException;
import org.project.narcoticsnexus.model.SellStats;
import org.project.narcoticsnexus.repo.OrderDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDetailsRepository orderRepository;
    private final CustomerService customerService;
    private final CartService cartService;
    private final WalletService walletService;
    private final ProductService productService;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)

    public void addOrder(String username, Long productId, Integer quantity) throws InsufficientStockException, InsufficientFundException {
        Customer customer = customerService.getCustomerByUsername(username);
        Product product = productService.getProductById(productId);
        Wallet wallet = walletService.getWalletByCustomer(username);
        LocalDate currentDate= LocalDate.now();
        OrderDetails order = OrderDetails.builder()
                .customer(customer)
                .product(product)
                .dateOfOrder(currentDate)
                .quantity(quantity)
                .build();
        if(product.getStock()<quantity){
            throw new InsufficientStockException();
        }
        if(wallet.getBalance()<(quantity*product.getCost())){
            log.info(wallet.getBalance() +" "+ quantity * product.getCost());
            throw new InsufficientFundException();
        }
        product.setStock(product.getStock()-quantity);
        productService.updateProduct(product,product.getVendor().getUsername());
        walletService.updateBalance(wallet.getId(), -(quantity * product.getCost()));
        orderRepository.save(order);
        customer.setNexusPoints(customer.getNexusPoints()+1);
        customerService.updateCustomer(customer,username);
    }
    public List<OrderDetails> getAllOrdersByCustomer(String username){
        Customer customer = customerService.getCustomerByUsername(username);
        return new ArrayList<>(orderRepository.findAllByCustomer(customer));
    }
    public List<OrderDetails> getOrdersByProduct(long productId){
        Product product = productService.getProductById(productId);
        return new ArrayList<>(orderRepository.findAllByProduct(product));
    }
    public void addCartOrder(String username) throws InsufficientFundException,InsufficientStockException{
        List<Cart> cartItems = cartService.getAllCartItemsByCustomer(username);
        for (Cart cartItem: cartItems){
            addOrder(username, cartItem.getProduct().getProductId(), cartItem.getQuantity());
            cartService.deleteCartItem(cartItem.getCartId());
        }
    }
    public SellStats getSellStats(String username, String productId) {
        List<OrderDetails> productOrders = getOrdersByProduct(Long.parseLong(productId));
        Product product = productService.getProductById(Long.parseLong(productId));
        int sale = 0;
        for(OrderDetails order:productOrders){
            sale += order.getQuantity();
        }
        float earnings = sale*product.getCost();
        return SellStats.builder()
                .vendorUsername(username)
                .productName(product.getProductName())
                .productId(Long.parseLong(productId))
                .sale(sale)
                .earnings(earnings)
                .build();
    }
}
