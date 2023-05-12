package net.etg.backendchallenge.controller;


import lombok.AllArgsConstructor;
import net.etg.backendchallenge.dto.CompanyDTO;
import net.etg.backendchallenge.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.createCompany(companyDTO));

    }

    @GetMapping("/get/{companyName}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable String companyName){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.getCompany(companyName));
    }

    @GetMapping("/get-all-companies")
    public ResponseEntity<List<CompanyDTO>> getCompanies(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.getAllCompanies());
    }

    @PutMapping("/update")
    public ResponseEntity<CompanyDTO> updateCompany(@RequestBody CompanyDTO companyDTO){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.updateCompany(companyDTO));
    }

    @DeleteMapping("/delete/{companyName}")
    public ResponseEntity<String> deleteCompany(@PathVariable String companyName){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.deleteCompany(companyName));
    }
}
