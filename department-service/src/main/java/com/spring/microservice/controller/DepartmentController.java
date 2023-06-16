package com.spring.microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microservice.dto.DepartmentDto;
import com.spring.microservice.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
@Tag(name = "Department Service - Department Controller", description = "REST APIs - Create & Get Department")
public class DepartmentController {

	private DepartmentService departmentService;

	// Build save department REST API
	@PostMapping
	@Operation(summary = "Create Department REST API", description = "Create Department REST API is used to save Department in a database")
	@ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
	public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
		DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
		return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
	}

	// Build get department rest api
	@GetMapping("{department-code}")
	@Operation(summary = "Get Department By Department Code REST API", description = "Get Department By Department Code REST API is used to get a single Department from the database")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode) {
		DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
		return new ResponseEntity<>(departmentDto, HttpStatus.OK);
	}
}
