package com.spring.microservice.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.microservice.dto.APIResponseDto;
import com.spring.microservice.dto.DepartmentDto;
import com.spring.microservice.dto.EmployeeDto;
import com.spring.microservice.dto.OrganizationDto;
import com.spring.microservice.entity.Employee;
import com.spring.microservice.exception.UserNotFoundException;
import com.spring.microservice.repository.EmployeeRepository;
import com.spring.microservice.service.EmployeeService;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private EmployeeRepository employeeRepository;

	// private RestTemplate restTemplate;

	private ModelMapper modelMapper;

	private WebClient webClient;

	// private APIFeignClient aPIFeignClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

		// Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

		Employee employee = modelMapper.map(employeeDto, Employee.class);

		Employee savedEmployee = employeeRepository.save(employee);

		// EmployeeDto savedEmployeeDto =
		// EmployeeMapper.mapToEmployeeDto(saveDEmployee);
		EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);

		return savedEmployeeDto;
	}

	// @CircuitBreaker(name = "${spring.application.name}", fallbackMethod =
	// "getDefaultDepartment")
	/*
	 * @Retry(name = "${spring.application.name}", fallbackMethod =
	 * "getDefaultDepartment")
	 * 
	 * @Override public APIResponseDto getEmployeeById(Long employeeId) {
	 * 
	 * LOGGER.info("inside getEmployeeById() method"); Employee employee =
	 * employeeRepository.findById(employeeId).get();
	 * 
	 * // ResponseEntity<DepartmentDto> responseEntity =
	 * restTemplate.getForEntity("http://DEPARTMENT-SERVICE/api/departments/" +
	 * employee.getDepartmentCode(), // DepartmentDto.class); // // DepartmentDto
	 * departmentDto = responseEntity.getBody();
	 * 
	 * DepartmentDto departmentDto = webClient.get()
	 * .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
	 * .retrieve() .bodyToMono(DepartmentDto.class) .block();
	 * 
	 * // DepartmentDto departmentDto =
	 * apiClient.getDepartment(employee.getDepartmentCode());
	 * 
	 * OrganizationDto organizationDto = webClient.get()
	 * .uri("http://localhost:8083/api/organizations/" +
	 * employee.getOrganizationCode()) .retrieve()
	 * .bodyToMono(OrganizationDto.class) .block();
	 * 
	 * EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
	 * 
	 * APIResponseDto apiResponseDto = new APIResponseDto();
	 * apiResponseDto.setEmployee(employeeDto);
	 * apiResponseDto.setDepartment(departmentDto);
	 * apiResponseDto.setOrganization(organizationDto); return apiResponseDto; }
	 */

	public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

		LOGGER.info("inside getDefaultDepartment() method");

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserNotFoundException("User " + employeeId + " Not Found"));

		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("R&D Department");
		departmentDto.setDepartmentCode("RD001");
		departmentDto.setDepartmentDescription("Research and Development Department");

		// EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);
		return apiResponseDto;
	}

	@Override
	// @CircuitBreaker(name = "${spring.application.name}", fallbackMethod =
	// "getDefaultDepartment")
	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	public APIResponseDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserNotFoundException("User " + employeeId + " Not Found"));

		// EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		LOGGER.info("inside getEmployeeById() method");

		/*
		 * ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
		 * "http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
		 * DepartmentDto.class); // DepartmentDto departmentDto =
		 * responseEntity.getBody();
		 */

		DepartmentDto departmentDto = webClient.get()
				.uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode()).retrieve()
				.bodyToMono(DepartmentDto.class).block();
		
		OrganizationDto organizationDto = webClient.get().uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode()).retrieve()
		.bodyToMono(OrganizationDto.class).block();

		// DepartmentDto departmentDto =
		// aPIFeignClient.getDepartment(employee.getDepartmentCode());
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);
		apiResponseDto.setOrganization(organizationDto);
		return apiResponseDto;
	}
}
