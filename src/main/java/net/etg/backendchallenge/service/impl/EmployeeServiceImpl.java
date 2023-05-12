package net.etg.backendchallenge.service.impl;

import lombok.AllArgsConstructor;
import net.etg.backendchallenge.dto.CompanyDTO;
import net.etg.backendchallenge.dto.EmployeeDTO;
import net.etg.backendchallenge.entity.Company;
import net.etg.backendchallenge.entity.Employee;
import net.etg.backendchallenge.exception.NoCompanyException;
import net.etg.backendchallenge.exception.NoEmployeeException;
import net.etg.backendchallenge.repository.CompanyRepository;
import net.etg.backendchallenge.repository.EmployeeRepository;
import net.etg.backendchallenge.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Optional<Company> optionalCompany = companyRepository.findCompanyByName(employeeDTO.getCompanyDTO().getCompanyName());
        if(optionalCompany.isPresent()){
            Employee employee = modelMapper.map(employeeDTO,Employee.class);
            employee.setCompany(optionalCompany.get());
            optionalCompany.get().add(employee);
            Employee savedEmployee = employeeRepository.save(employee);
            EmployeeDTO returnedEmployeeDTO = modelMapper.map(savedEmployee,EmployeeDTO.class);
            returnedEmployeeDTO.setCompanyDTO(modelMapper.map(optionalCompany.get(), CompanyDTO.class));
            return returnedEmployeeDTO;
        }else{
            throw new NoCompanyException();
        }

    }

    @Override
    public EmployeeDTO getEmployee(Long employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(NoEmployeeException::new);
        EmployeeDTO employeeDTO = modelMapper.map(existingEmployee,EmployeeDTO.class);
        employeeDTO.setCompanyDTO(modelMapper.map(existingEmployee.getCompany(), CompanyDTO.class));
        return employeeDTO;
    }


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees
                .stream()
                .map(employee -> modelMapper.map(employee,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(employeeDTO.getId()).orElseThrow(NoEmployeeException::new);
        Optional<Company> optionalCompany = companyRepository.findCompanyByName(employeeDTO.getCompanyDTO().getCompanyName());
        if(optionalCompany.isPresent()){
            existingEmployee.setCompany(optionalCompany.get());
            existingEmployee.setFirstName(employeeDTO.getFirstName());
            existingEmployee.setLastName(employeeDTO.getLastName());
            optionalCompany.get().add(existingEmployee);
            EmployeeDTO returnedEmployeeDTO = modelMapper.map(employeeRepository.save(existingEmployee),EmployeeDTO.class);
            returnedEmployeeDTO.setCompanyDTO(modelMapper.map(optionalCompany.get(), CompanyDTO.class));
            return returnedEmployeeDTO;
        }else{
            throw new NoCompanyException();
        }


    }

    @Override
    public String deleteEmployee(Long employeeId) {
        Optional<net.etg.backendchallenge.entity.Employee> optionalCompany = employeeRepository.findById(employeeId);
        if(optionalCompany.isPresent()){
            employeeRepository.delete(optionalCompany.get());
            return "Employee is deleted successfully";
        }
        throw new NoEmployeeException();
    }
}
