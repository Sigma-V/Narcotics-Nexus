package org.project.narcoticsnexus.controller;

import lombok.RequiredArgsConstructor;
import org.project.narcoticsnexus.entity.Product;
import org.project.narcoticsnexus.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @RequestMapping(method = RequestMethod.GET, value = "/products/name/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name){
        return ResponseEntity.ok(productService.getProductsByName(name));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/products/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category){
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/products/id/{productId}")
    public Product getProductById(@PathVariable String productId){
        return productService.getProductById(Long.parseLong(productId));
    }
}
