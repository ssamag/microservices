package com.spring.microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microservice.dto.APIResponseDto;
import com.spring.microservice.dto.EmployeeDto;
import com.spring.microservice.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
@Tag(name = "Employee Service - Employee Controller", description = "REST APIs - Create & Get Employee")
public class EmployeeController {

    private EmployeeService employeeService;

    // Build Save Employee REST API
    @PostMapping
    @Operation(summary = "Create Employee REST API", description = "Create Employee REST API is used to save Employee in a database")
	@ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build Get Employee REST API
    @GetMapping("{id}")
    @Operation(summary = "Get Employee By Employee Id REST API", description = "Get Employee By Employee Id REST API is used to get a single Employee from the database")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId){
    	APIResponseDto aPIResponseDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(aPIResponseDto, HttpStatus.OK);
    }
}
