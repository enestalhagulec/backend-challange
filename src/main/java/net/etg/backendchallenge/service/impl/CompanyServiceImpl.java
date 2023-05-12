package net.etg.backendchallenge.service.impl;

import lombok.AllArgsConstructor;
import net.etg.backendchallenge.dto.CompanyDTO;
import net.etg.backendchallenge.entity.Company;
import net.etg.backendchallenge.exception.NoCompanyException;
import net.etg.backendchallenge.repository.CompanyRepository;
import net.etg.backendchallenge.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO){
        Company company = modelMapper.map(companyDTO, Company.class);
        return modelMapper.map(companyRepository.save(company),CompanyDTO.class);
    }

    @Override
    public CompanyDTO getCompany(String companyName){
        Company existingCompany = companyRepository.findCompanyByName(companyName).orElseThrow(NoCompanyException::new);
        return modelMapper.map(existingCompany,CompanyDTO.class);
    }

    @Override
    public List<CompanyDTO> getAllCompanies(){
        List<Company> companies = companyRepository.findAll();

        return companies
                .stream()
                .map(company -> modelMapper.map(company,CompanyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO){
        Company existingCompany = companyRepository.findById(companyDTO.getId()).orElseThrow(NoCompanyException::new);
        existingCompany.setCompanyName(companyDTO.getCompanyName());
        existingCompany.setLocation(companyDTO.getLocation());
        existingCompany.setIndustry(companyDTO.getIndustry());
        return modelMapper.map(companyRepository.save(existingCompany),CompanyDTO.class);
    }

    @Override
    public String deleteCompany(String companyName){
        Optional<Company> optionalCompany = companyRepository.findCompanyByName(companyName);
        if(optionalCompany.isPresent()){
            companyRepository.delete(optionalCompany.get());
            return "Company is deleted successfully";
        }
        throw new NoCompanyException();
    }








}
