package net.etg.backendchallenge.dto;

import lombok.Data;
import net.etg.backendchallenge.entity.Employee;

import java.util.List;

@Data
public class CompanyDTO {

    private Long id;
    private String companyName;
    private String location;
    private String industry;
    private List<Employee> employees;
}
