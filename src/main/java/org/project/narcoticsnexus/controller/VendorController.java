package org.project.narcoticsnexus.controller;

import lombok.RequiredArgsConstructor;
import org.project.narcoticsnexus.entity.Product;
import org.project.narcoticsnexus.entity.Vendor;
import org.project.narcoticsnexus.model.SellStats;
import org.project.narcoticsnexus.service.OrderService;
import org.project.narcoticsnexus.service.ProductService;
import org.project.narcoticsnexus.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;
    private final ProductService productService;
    private final OrderService orderService;

    @RequestMapping(method = RequestMethod.PUT, value = "/user/vendor/{username}")
    public void updateVendorDetails(@RequestBody Vendor vendor, @PathVariable String username){
        if(vendorService.getVendorByUsername(username)!=null){
            vendorService.updateVendor(vendor);
        }
    }

    // Product related mappings
    @RequestMapping(method = RequestMethod.POST, value="/user/vendor/{username}/product")
    public void addProduct(@RequestBody Product product, @PathVariable String username){
        productService.addProduct(product,username);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/user/vendor/{username}/product")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String username){
        return ResponseEntity.ok(productService.getProductsByVendor(username));
    }
    @RequestMapping(method=RequestMethod.PUT, value="/user/vendor/{username}/product")
    public void updateProduct(@RequestBody Product product, @PathVariable String username){
        productService.updateProduct(product, username);
    }
    @RequestMapping(method = RequestMethod.PUT, value ="/user/vendor/{username}/product/{productId}/restock/{stock}")
    public void reStockProduct(@PathVariable String productId, @PathVariable String stock, @PathVariable String username){
        productService.reStockProduct(productId,stock,username);
    }
    @RequestMapping(method=RequestMethod.DELETE, value = "/user/vendor/{username}/product/{productId}")
    public void removeProduct(@PathVariable String username, @PathVariable String productId){
        productService.removeProduct(Long.parseLong(productId));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/user/vendor/{username}/product/{productId}/stats")
    public SellStats getSellStats(@PathVariable String username, @PathVariable String productId){
        return orderService.getSellStats(username,productId);
    }

}
