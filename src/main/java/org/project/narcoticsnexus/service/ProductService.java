package org.project.narcoticsnexus.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.project.narcoticsnexus.entity.Product;
import org.project.narcoticsnexus.entity.Vendor;
import org.project.narcoticsnexus.repo.ProductRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final VendorService vendorService;
    private final JdbcClient jdbcClient;
    public void addProduct(Product product, String username){
        product.setVendor(Vendor.builder().username(username).build());
        productRepository.save(product);
    }
    public Product getProductById(long productId){
        return productRepository.findById(productId).orElse(new Product());
    }
    public List<Product> getAllProducts(){
        return new ArrayList<>(jdbcClient.sql("SELECT * FROM product").query(Product.class).list());
    }
    public List<Product> getProductsByName(String productName){
        return new ArrayList<>(productRepository.findAllByProductName(productName));
    }
    public List<Product> getProductsByVendor(String username){
        Vendor vendor = vendorService.getVendorByUsername(username);
        return new ArrayList<>(productRepository.findAllByVendor(vendor));
    }
    public List<Product> getProductsByCategory(String category){
        return new ArrayList<>(productRepository.findAllByCategory(category));
    }
    public void updateProduct(Product product, String username){
        product.setVendor(Vendor.builder().username(username).build());
        productRepository.save(product);
    }
    public void removeProduct(Long productId){
        Product product=getProductById(productId);
        product.setStock(0);
        productRepository.save(product);
    }

    @Transactional
    public void reStockProduct(String productId, String stock, String username) {
        Product product=getProductById(Long.parseLong(productId));
        product.setStock(Integer.parseInt(stock));
        productRepository.save(product);
    }
}
