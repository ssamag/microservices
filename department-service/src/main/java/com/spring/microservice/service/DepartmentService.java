package com.spring.microservice.service;

import com.spring.microservice.dto.DepartmentDto;

public interface DepartmentService {
	
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);
}
