package com.erc.controller;

import com.erc.model.Product;
import com.erc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search/{productType}")
    public ResponseEntity<?> getProducts(@PathVariable String productType) {

        List<Product> productList = productService.filterByProductType(productType);

        return ResponseEntity.ok(productList);

    }

    @GetMapping("/filter")
    public ResponseEntity<?> findProduct(@RequestParam(value ="productType", required = false )  String productType) {

        List<Product> byService = productType == null ? productService.getProductList() : productService.filterByProductType(productType);

        return ResponseEntity.ok(byService);
    }
}
