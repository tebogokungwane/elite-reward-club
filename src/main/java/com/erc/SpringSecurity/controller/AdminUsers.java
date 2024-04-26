//package com.erc.SpringSecurity.controller;
//
//import com.erc.SpringSecurity.entity.Product;
//import com.erc.SpringSecurity.repository.ProductRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import com.erc.SpringSecurity.dto.ReqRes;
//
//@RestController
//public class AdminUsers {
//
//    @Autowired
//    private ProductRepo productRepo;
//
//    @GetMapping("/public/product")
//    public ResponseEntity<Object> getAllProducts(){
//        return ResponseEntity.ok(productRepo.findAll());
//    }
//
//    @PostMapping("/admin/saveproduct")
//    public ResponseEntity<Object> signUp(@RequestBody ReqRes productRequest){
//        Product productToSave = new Product();
//        productToSave.setName(productRequest.getName());
//        return ResponseEntity.ok(productRepo.save(productToSave));
//    }
//
//    @GetMapping("/user/alone")
//    public ResponseEntity<Object> userAlone(){
//        return ResponseEntity.ok("Users alone can access this API only");
//    }
//
//    @GetMapping("/adminuser/both")
//    public ResponseEntity<Object> bothAdminAndUserApi(){
//        return ResponseEntity.ok("Both Admin and Users can access this Api");
//    }
//}
