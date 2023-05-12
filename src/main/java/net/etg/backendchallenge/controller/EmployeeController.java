package net.etg.backendchallenge.controller;

import lombok.AllArgsConstructor;
import net.etg.backendchallenge.dto.EmployeeDTO;
import net.etg.backendchallenge.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(employeeDTO));
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long employeeId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.getEmployee(employeeId));

    }

    @GetMapping("/get-all-employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployees(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.getAllEmployees());
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.updateEmployee(employeeDTO));
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.deleteEmployee(employeeId));
    }
}
