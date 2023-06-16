package com.spring.microservice.organizationservice.service;

import com.spring.microservice.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
	
    public OrganizationDto saveOrganization(OrganizationDto organizationDto);

    public OrganizationDto getOrganizationByCode(String organizationCode);
}
