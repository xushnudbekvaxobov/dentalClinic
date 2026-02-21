package clinicManagement.service;

import clinicManagement.dto.requestDto.EmployeeCreateDto;
import clinicManagement.dto.requestDto.EmployeeDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    ResponseEntity<ApiResponse<?>> createEmployee(EmployeeCreateDto employeeDto);
    ResponseEntity<ApiResponse<?>> getAllEmployee();
    ResponseEntity<ApiResponse<?>> getEmployeeById(Long id);
    ResponseEntity<ApiResponse<?>> updateEmployee(EmployeeDto employeeDto, Long id);
}
