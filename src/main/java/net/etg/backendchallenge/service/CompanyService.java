package net.etg.backendchallenge.service;


import net.etg.backendchallenge.dto.CompanyDTO;
import net.etg.backendchallenge.entity.Company;

import java.util.List;

public interface CompanyService {

    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO getCompany(String companyName);
    List<CompanyDTO> getAllCompanies();
    CompanyDTO updateCompany(CompanyDTO companyDTO);
    String deleteCompany(String companyName);
}
