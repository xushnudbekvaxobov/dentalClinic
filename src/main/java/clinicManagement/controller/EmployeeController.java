package clinicManagement.controller;

import clinicManagement.dto.requestDto.EmployeeCreateDto;
import clinicManagement.dto.requestDto.EmployeeDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
        private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody EmployeeCreateDto employeeCreateDto){
        return employeeService.createEmployee(employeeCreateDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<?>> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<?>> updateById(@RequestBody EmployeeDto employeeDto,@PathVariable Long id){
        return employeeService.updateEmployee(employeeDto,id);
    }




}
