package com.erc.service;

import com.erc.exception.ProductNotFoundException;
import com.erc.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    List<Product> productList = new ArrayList<>();

    public ProductService() {
        productList.add(new Product(1L, "Laptop", 999.99, 10, "Electronics"));
        productList.add(new Product(2L, "Smartphone", 599.99, 20, "Electronics"));
        productList.add(new Product(3L, "Book", 19.99, 50, "Books"));
        productList.add(new Product(4L, "Chair", 79.99, 30, "Furniture"));
    }

    public List<Product> filterByProductType(String productType) {

        List<Product> products = productList.stream().filter(product -> product.getProductType().equalsIgnoreCase(productType))
                .collect(Collectors.toList());

        return Optional.of(products)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ProductNotFoundException("Product type not found" + productType));


    }

    public List<Product> getProductList(){
        return productList;
    }


}
