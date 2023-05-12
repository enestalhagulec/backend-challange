package net.etg.backendchallenge.service;


import net.etg.backendchallenge.dto.EmployeeDTO;
import net.etg.backendchallenge.entity.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employee);

    EmployeeDTO getEmployee(Long employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    String deleteEmployee(Long employeeId);

}
