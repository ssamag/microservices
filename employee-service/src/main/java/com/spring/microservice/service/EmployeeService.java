package com.spring.microservice.service;

import com.spring.microservice.dto.APIResponseDto;
import com.spring.microservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
