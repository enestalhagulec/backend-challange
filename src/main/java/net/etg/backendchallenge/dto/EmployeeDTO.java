package net.etg.backendchallenge.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private CompanyDTO companyDTO;

}
