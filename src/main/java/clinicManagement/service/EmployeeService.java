package clinicManagement.service;

import clinicManagement.dto.requestDto.EmployeeCreateDto;
import clinicManagement.dto.requestDto.EmployeeDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.EmployeeResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeCreateDto employeeDto);
    ResponseEntity<ApiResponse<?>> getAllEmployee();
    ResponseEntity<ApiResponse<?>> getEmployeeById(UUID id);
    ResponseEntity<ApiResponse<?>> updateEmployee(EmployeeDto employeeDto, UUID id);
}
