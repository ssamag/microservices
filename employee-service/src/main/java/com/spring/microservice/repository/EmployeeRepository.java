package com.spring.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.microservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
