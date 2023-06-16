package com.spring.microservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.microservice.dto.DepartmentDto;

//@FeignClient(url = "http://localhost:8080", value= "DEPARTMENT-SERVICE")
@FeignClient(name= "DEPARTMENT-SERVICE")
public interface APIFeignClient {
	
	@GetMapping("api/departments/{department-code}")
    public DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);

}
