//package com.erc.controller;
//
//import com.erc.entity.Company;
//import com.erc.service.CompanyService;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@RestController
//@RequestMapping("/api/v1")
////@PreAuthorize("hasRole('Admin')")
//public class CompanyController {
//
//    @Autowired
//    private CompanyService companyService;
//
//    @GetMapping("/findAllCompanies")
//  //  @PreAuthorize("hasAuthority('admin:read')")
//    public ResponseEntity<List<Company>> findAllCompanies() {
//        return ResponseEntity.ok(companyService.findAllCompanies());
//    }
//
////    @GetMapping("/findByRoleAndCompanyName")
//// //   @PreAuthorize("hasAuthority('admin:read')")
////    public ResponseEntity<List<User>> getAllByRoleAndCompanyList_CompanyName(@RequestBody CompanyNameAndRoleRequestPayload nameAndRoleRequestPayload) {
////        List<User> allByRoleAndCompanyListCompanyName = companyService.getAllByRoleAndCompanyList_CompanyName(nameAndRoleRequestPayload.getRole(), nameAndRoleRequestPayload.getCompanyName());
////        return ResponseEntity.ok(allByRoleAndCompanyListCompanyName);
////    }
//}
