package com.spring.microservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.spring.microservice.dto.DepartmentDto;
import com.spring.microservice.entity.Department;
import com.spring.microservice.exception.DepartmentNotFoundException;
import com.spring.microservice.repository.DepartmentRepository;
import com.spring.microservice.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        // convert department dto to department jpa entity
        //Department department = DepartmentMapper.mapToDepartment(departmentDto);
    	Department department = modelMapper.map(departmentDto,Department.class);

        Department savedDepartment = departmentRepository.save(department);

        //DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);
        DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment,DepartmentDto.class);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
    	Department department = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(() -> 
		new DepartmentNotFoundException("Department " + departmentCode +" Not Found"));

        //DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);
        DepartmentDto departmentDto = modelMapper.map(department,DepartmentDto.class);


        return departmentDto;
    }
}
